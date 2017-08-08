package com.netease.httpclient;

import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * http工具类
 * @Author: zhouwen
 * @Date: 2017/8/8 16:07
 */
public class HttpUtils {

    /**
     * 发送一个post请求
     * @param url 请求url
     * @param params 请求参数
     * @return
     */
    public static String httpPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if (null == url || url.length() <= 0)
            throw new RuntimeException("url can not null");
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (MapUtils.isNotEmpty(params)) {
            params.forEach((key, value) -> nameValuePairs.add(new BasicNameValuePair(key, value)));
        }
        CloseableHttpResponse response = null;
        String content = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                content = "http return code is not 200 : " + response.getStatusLine().getStatusCode();
                return content;
            }
            HttpEntity httpEntity = response.getEntity();
            if (null == httpEntity) {
                content = "http response entity is null.";
                return content;
            }
            content = new BufferedReader(new InputStreamReader(httpEntity.getContent())).lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
            content = "http post error.";
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
