package com.netease.httpclient;

import org.apache.http.HttpEntity;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Description: Created with IntelliJ IDEA.
 * @Author: zhouwen
 * @Date: 2017/8/8 17:04
 */
public class HttpUtilsTest {

    @Test
    public void httpPost() throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("appName", "lofter");
        params.put("scene", "suggest_blog");
        params.put("account", "12356@qq.com");
        params.put("num", "5");

        String url = "http://127.0.0.1:8080/lofterrec/r/r.s";

        String content = HttpUtils.httpPost(url, params);
        System.out.println(content);
    }

}