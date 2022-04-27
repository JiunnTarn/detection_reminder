package com.jiunntarn.detection_reminder.util;

import com.google.gson.Gson;
import com.jiunntarn.detection_reminder.pojo.MyCookieJar;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class OkHttpUtil<JavaNetCookieJar> {
    public final static Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);
    /**
     * 最大连接时间
     */
    public final static int CONNECTION_TIMEOUT = 5;
    /**
     * JSON格式
     */
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * OkHTTP线程池最大空闲线程数
     */
    public final static int MAX_IDLE_CONNECTIONS = 100;
    /**
     * OkHTTP线程池空闲线程存活时间
     */
    public final static long KEEP_ALIVE_DURATION = 30L;
    /**
     * GSON格式
     */
    public static final Gson GSON = new Gson();

    public static String BASE64_PREFIX = "data:image/png;base64,";

    /**
     * client
     * 配置重试
     */
    static MyCookieJar cookieJar = new MyCookieJar() {
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieStore.put(url, cookies);
            cookieStore.put(HttpUrl.parse("https://pass.sdu.edu.cn/cas/login"), cookies);
            for(Cookie cookie:cookies){
//                        System.out.println("cookie Name:"+cookie.name());
//                        System.out.println("cookie Value:"+cookie.value());
//                        System.out.println("cookie Path:"+cookie.path());
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(HttpUrl.parse("https://pass.sdu.edu.cn/cas/login"));
            if(cookies==null){
//                        System.out.println("没加载到cookie");
            }
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }

        @Override
        public void clearCookieJar() {
            cookieStore.clear();
        }

    };

    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
//            .followRedirects(false)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .connectionPool(new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MINUTES))
            .build();

    public static void clearCookieJar() {
        try {
            cookieJar.clearCookieJar();
        } catch (Exception e) {
            logger.error("清除CookieJar失败", e);
        }
    }


    /**
     * get请求，无需转换对象
     *
     * @param url     链接
     * @param headers 请求头
     * @return 响应信息
     */
    public static Map<String, Object> get(String url, Map<String, String> headers) {
        try {
            Request.Builder builder = new Request.Builder();
            buildHeader(builder, headers);
            Request request = builder.url(url).build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            Map result = new HashMap();

            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                result.put("body", response.body().string());
                result.put("cookie", response.headers("Set-Cookie"));
                logger.info("执行get请求, url: {} 成功", url);
                return result;
            }

        } catch (Exception e) {
            logger.error("执行get请求，url: {} 失败!", url, e);
        }
        return null;
    }

    /**
     * Form表单提交
     *
     * @param url    地址
     * @param params form参数
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        try {

            FormBody.Builder builder = new FormBody.Builder();
            if (!CollectionUtils.isEmpty(params)) {
                params.forEach(builder::add);
            }
            FormBody body = builder.build();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            //调用成功
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }


    /**
     * 简单post请求
     *
     * @param url     请求url
     * @param headers 请求头
     * @param json    请求参数
     * @return
     */
    public static String post(String url, Map<String, String> headers, String json) {
        try {
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
            Request.Builder builder = new Request.Builder();
            buildHeader(builder, headers);
            Request request = builder.url(url).post(body).build();
            Response response = HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                String result = response.body().string();
                logger.info("执行post请求,url: {}, 成功，返回结果: {}", url, result);
                return result;
            }
        } catch (Exception e) {
            logger.error("执行post请求，url: {},参数: {} 失败!", url, json, e);
        }
        return null;
    }


    /**
     * 设置请求头
     *
     * @param builder .
     * @param headers 请求头
     */
    private static void buildHeader(Request.Builder builder, Map<String, String> headers) {
        if (Objects.nonNull(headers) && headers.size() > 0) {
            headers.forEach((k, v) -> {
                if (Objects.nonNull(k) && Objects.nonNull(v)) {
                    builder.addHeader(k, v);
                }
            });
        }
    }


    /**
     * 支持嵌套泛型的post请求。
     * <pre>
     *   Type type = new TypeToken<Results<User>>() {}.getType();
     * <pre/>
     *
     * @param url     链接
     * @param headers 请求头
     * @param json    请求json
     * @param type    嵌套泛型
     * @return 响应对象, 可进行强转。
     */
    public static <T> T post(String url, Map<String, String> headers, String json, Type type) {
        String result = post(url, headers, json);
        if (Objects.nonNull(result) && Objects.nonNull(type)) {
            return GSON.fromJson(result, type);
        }
        return null;
    }
    /**
     * 读取流，转换为Base64
     * 返回base64的照片
     */
    public static String postImg(String url, Map<String, String> headers, String json) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request.Builder builder = new Request.Builder();
        buildHeader(builder, headers);
        Request request = builder.url(url).post(body).build();
        try {
            Response response = HTTP_CLIENT.newCall(request).execute();
            String contentType = null;
            if (response.body() != null && response.body().contentType() != null) {
                contentType = response.body().contentType().toString();
            }
            if ("image/png".equals(contentType)) {
                //读取流
                return BASE64_PREFIX + new String(Base64.getEncoder().encode(response.body().bytes()));
            }
        } catch (IOException e) {
            logger.error("", e);
        }
        return null;
    }


    /**
     * 异步获取照片地址
     */
    public static void downloadImg(String url, String json) {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);

        Request request = new Request.Builder().url(url).post(body).build();

        Call call = HTTP_CLIENT.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //打印异常
                logger.error("", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = BASE64_PREFIX + new String(Base64.getEncoder().encode(response.body().bytes()));
                logger.info(s);
            }
        });

    }
}