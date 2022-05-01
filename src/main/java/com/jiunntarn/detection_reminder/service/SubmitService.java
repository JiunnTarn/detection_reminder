package com.jiunntarn.detection_reminder.service;

import com.jiunntarn.detection_reminder.controller.ClearCookieJarController;
import com.jiunntarn.detection_reminder.setting.Time;
import com.jiunntarn.detection_reminder.util.DesUtil;
import com.jiunntarn.detection_reminder.util.OkHttpUtil;
import com.jiunntarn.detection_reminder.util.TimeUtil;

import org.springframework.stereotype.Controller;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author JiunnTarn
 */
@Controller
public class SubmitService {
    public static String submitService(String XM, String u, String p) throws IOException {
        ClearCookieJarController.clearCookieJar();
        String passUrl = "https://pass.sdu.edu.cn/cas/login?service=https%3A%2F%2Fscenter.sdu.edu.cn%2Ftp_fp%2FformParser%3Fstatus%3Dselect%26formid%3Db65299ad-08f8-4b12-8053-95193ca0%26service_id%3D286d0fcb-78fe-435e-94f8-1657e07431df%26process%3Db7f71cba-af72-4b20-99b2-33041fdc3fd9%26seqId%3D%26seqPid%3D%26privilegeId%3D735977715622f326dea2605011897145";
        Map<String, String> header = new HashMap<>();
        String passInfo = (String) Objects.requireNonNull(OkHttpUtil.get(passUrl, header)).get("body");
        String lt = passInfo.split("\"lt\" value=\"")[1].substring(0, 44);
        String execution = passInfo.split("\"execution\" value=\"")[1].substring(0, 4);

        String ul = String.valueOf(u.length());
        String pl = String.valueOf(p.length());
        String _eventId = "submit";

        String rsa = DesUtil.strEnc(u + p + lt);

        Map<String, String> params = new HashMap<>();
        params.put("rsa", rsa);
        params.put("ul", ul);
        params.put("pl", pl);
        params.put("lt", lt);
        params.put("execution", execution);
        params.put("_eventId", _eventId);


        String loginInfo = OkHttpUtil.post(passUrl, params);

        assert loginInfo != null;

        String pk_id_temp;
        pk_id_temp = loginInfo.split("\"pk_id\":\"")[1];

        String pk_id = StringUtils.substringBefore(pk_id_temp, "\"");

        String fk_id_temp = loginInfo.split("\"fk_id\":\"")[1];
        String fk_id = StringUtils.substringBefore(fk_id_temp, "\"");
        String record_fk = fk_id;

        String requestText = "{header:{\"code\":0,\"message\":{\"title\":\"\",\"detail\":\"\"}},body:{dataStores:{\"variable\":{rowSet:{\"primary\":[{\"name\":\"JCDD\",\"source\":\"process\",\"type\":\"string\",\"value\":\"校内\",\"_t\":1,_o : {\"value\":\"\"}},{\"name\":\"BUSINESS_UNIT\",\"source\":\"process\",\"type\":\"string\",\"value\":\"\"},{\"name\":\"XM\",\"source\":\"process\",\"type\":\"string\",\"value\":\"" + XM + "\",\"_t\":1,_o : {\"value\":\"\"}},{\"name\":\"SFLX\",\"source\":\"process\",\"type\":\"string\",\"value\":\"本科生\",\"_t\":1,_o : {\"value\":\"\"}},{\"name\":\"SYS_USER\",\"source\":\"interface\",\"type\":\"string\",\"value\":\"" + XM + "\"},{\"name\":\"SYS_UNIT\",\"source\":\"interface\",\"type\":\"string\",\"value\":\"软件学院 \"},{\"name\":\"SYS_DATE\",\"source\":\"interface\",\"type\":\"date\",\"value\":\"" + TimeUtil.nowMilliseconds() + "\"},{\"name\":\"1189060d-4465-4b53-89c2-c9f88457.ID_NUMBER\",\"value\":\"" + u + "\"},{\"name\":\"1189060d-4465-4b53-89c2-c9f88457.USER_NAME\",\"value\":\"" + XM + "\"},{\"name\":\"1189060d-4465-4b53-89c2-c9f88457.UNIT_NAME\",\"value\":\"软件学院\"},{\"name\":\"4f613836-07ae-4f1b-91ee-2723d91f.SFLX\",\"value\":\"本科生\"},{\"name\":\"343750699122688.SYSDATE\",\"value\":\"" + TimeUtil.nowMilliseconds() + "\"}],\"filter\":[],\"delete\":[]},name:\"variable\",pageNumber:1,pageSize:2147483647,recordCount:0,parameters:{}},\"08f29f32-ece1-4913-8c36-c4821617_record\":{rowSet:{\"primary\":[{\"CFCSSQ_TEXT\":\"请选择\",\"pk_id\":\"" + pk_id + "\",\"CFCSSF\":\"\",\"CFCSX_TEXT\":\"请选择\",\"JCRQ_STR\":\"" + TimeUtil.todayFormat() + " 00:00:00\",\"CFCSSF_TEXT\":\"请选择\",\"DW\":\"软件学院\",\"XM\":\"" + XM + "\",\"CFCSX\":\"\",\"XGH\":\"" + u + "\",\"SFLX\":\"本科生\",\"fk_id\":\"" + fk_id + "\",\"JCRQ\":\"" + TimeUtil.todayMilliseconds() + "\",\"JCDD\":\"校内\",\"CFCSSQ\":\"\",\"XXDZ\":\"\"}],\"filter\":[],\"delete\":[]},name:\"08f29f32-ece1-4913-8c36-c4821617_record\",pageNumber:1,pageSize:2147483647,recordCount:0,rowSetName:\"d9d48533-bec6-4b2e-918b-17cf7770\",parameters:{\"exist\":true,\"relatedcontrols\":\"body_0\",\"primarykey\":\"pk_id\",\"queryds\":\"08f29f32-ece1-4913-8c36-c4821617\"}},\"uploader_0_record\":{rowSet:{\"primary\":[],\"filter\":[],\"delete\":[]},name:\"uploader_0_record\",pageNumber:1,pageSize:2147483647,recordCount:0,parameters:{}},\"08f29f32-ece1-4913-8c36-c4821617\":{rowSet:{\"primary\":[{\"XGH\":\"" + u + "\",\"_t\":3,\"XM\":\"" + XM + "\",\"DW\":\"软件学院\",\"SFLX\":\"本科生\",\"CFCSSF_TEXT\":\"请选择\",\"CFCSSF\":\"\",\"CFCSSQ_TEXT\":\"请选择\",\"CFCSSQ\":\"\",\"CFCSX_TEXT\":\"请选择\",\"CFCSX\":\"\",\"XXDZ\":\"\",\"JCRQ\":\"2022-04-27\",\"JCDD\":\"校内\",_o : {\"XGH\":null,\"XM\":null,\"DW\":null,\"SFLX\":null,\"CFCSSF_TEXT\":null,\"CFCSSF\":null,\"CFCSSQ_TEXT\":null,\"CFCSSQ\":null,\"CFCSX_TEXT\":null,\"CFCSX\":null,\"XXDZ\":null,\"JCRQ\":null,\"JCDD\":null}}],\"filter\":[],\"delete\":[]},name:\"08f29f32-ece1-4913-8c36-c4821617\",pageNumber:1,pageSize:2147483647,recordCount:1,rowSetName:\"d9d48533-bec6-4b2e-918b-17cf7770\",parameters:{\"relatedcontrols\":\"body_0\",\"primarykey\":\"pk_id\",\"queryds\":\"08f29f32-ece1-4913-8c36-c4821617\"}},\"uploader_0\":{rowSet:{\"primary\":[{\"_t\":1}],\"filter\":[],\"delete\":[]},name:\"uploader_0\",pageNumber:1,pageSize:2147483647,recordCount:0,parameters:{\"uploader\":\"true\",\"exist\":\"true\"}}},parameters:{\"formid\":\"b65299ad-08f8-4b12-8053-95193ca0\",\"status\":\"select\",\"privilegeId\":\"735977715622f326dea2605011897145\",\"seqId\":\"\",\"service_id\":\"286d0fcb-78fe-435e-94f8-1657e07431df\",\"process\":\"b7f71cba-af72-4b20-99b2-33041fdc3fd9\",\"seqPid\":\"\",\"record_fk\":\"" + record_fk + "\",\"strUserId\":\"\",\"strUserIdCC\":\"\",\"nextActId\":\"\"}}}";

        String submitUrl = "https://scenter.sdu.edu.cn/tp_fp/formParser?status=update&formid=b65299ad-08f8-4b12-8053-95193ca0&workflowAction=startProcess&seqId=&unitId=&workitemid=&process=b7f71cba-af72-4b20-99b2-33041fdc3fd9";
        return OkHttpUtil.post(submitUrl, null, requestText);


    }
}
