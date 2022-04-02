package com.JiunnTarn.sign;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;

/**
 * @author JiunnTarn
 */
public class Sign {
    public static String sign() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String secret = "SECabea2e0ed34308b0bfd1faff0af747151f36ef7450168401c59baa9d7a3da65a";

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return "&timestamp=" + timestamp + "&sign=" + sign;
    }

}