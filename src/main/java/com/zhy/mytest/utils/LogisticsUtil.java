package com.zhy.mytest.utils;

import org.apache.http.HttpException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogisticsUtil {
    public static final String QUERYURL = "http://www.kuaidi100.com/query?";
 
    public static String setUrl(String logisticsCode, String logisticsNo) {
        String temp = String.valueOf(Math.random());
        StringBuilder sb = new StringBuilder(QUERYURL);
        sb.append("tpye=").append(logisticsCode).append("&");
        sb.append("postid=").append(logisticsNo).append("&");
        sb.append("temp=").append(temp);
        return sb.toString();
    }
 
    public static String queryData(String logisticsCode, String logisticsNo) {
        String line = "";
        String temp = String.valueOf(Math.random());
        String url = "http://www.kuaidi100.com/query?type=" + logisticsCode + "&postid=" + logisticsNo + "&temp=" + temp + "&phone=";
        try {
            URL realURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realURL.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    return line;
                }
                reader.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return line;
 
    }
 
    public static void main(String[] args) throws HttpException, IOException {
        System.out.println(queryData("yunda", "4312130885344"));
    }
 
}