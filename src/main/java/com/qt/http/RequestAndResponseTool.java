package com.qt.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class RequestAndResponseTool {


    public static Page  sendRequstAndGetResponse(String url) {
        Page page = null;
        // 1.生成 HttpClinet 对象并设置参数
        HttpClient httpClient = new HttpClient();
        // 设置 HTTP 连接超时 5s
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        // 2.生成 GetMethod 对象并设置参数
        GetMethod getMethod = new GetMethod(url);
//        // 设置 get 请求超时 5s
//        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
//        // 设置请求重试处理
//        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        getMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        getMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
        getMethod.setRequestHeader("Accept-Language","zh-CN,zh;q=0.9");
        getMethod.setRequestHeader("Cookie","ASP.NET_SessionId=l1bk4o555iwr23burpoiobek");
        getMethod.setRequestHeader("Host","search.anccnet.com");
        getMethod.setRequestHeader("Proxy-Connection","keep-alive");
        getMethod.setRequestHeader("Referer","http://search.anccnet.com/searchResult2.aspx?keyword=6924168200161");
        getMethod.setRequestHeader("Upgrade-Insecure-Requests","1");
        getMethod.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
        // 3.执行 HTTP GET 请求
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            // 判断访问的状态码
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + getMethod.getStatusLine());
            }
            // 4.处理 HTTP 响应内容
            byte[] responseBody = getMethod.getResponseBody();// 读取为字节 数组
            String contentType = getMethod.getResponseHeader("Content-Type").getValue(); // 得到当前返回类型
            page = new Page(responseBody,url,"gb2312"); //封装成为页面
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
            e.printStackTrace();
        } catch (IOException e) {
            // 发生网络异常
            e.printStackTrace();
        } finally {
            // 释放连接
            getMethod.releaseConnection();
        }
        return page;
    }
}