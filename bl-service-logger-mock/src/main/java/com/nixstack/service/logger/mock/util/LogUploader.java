package com.nixstack.service.logger.mock.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LogUploader {
    public static final void sendLogStream(String log) {
        try {
            URL url = new URL("http://log.nixstack.tk");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //时间头用来给server进行时钟校对
            conn.setRequestProperty("clientTime",System.currentTimeMillis() + "");
            //允许上传数据
            conn.setDoOutput(true);
            //设置请求的头信息
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //输出流
            OutputStream out = conn.getOutputStream();
            out.write(("log="+log).getBytes());
            out.flush();
            out.close();

            // 必须有，否则不打印日志
            int code = conn.getResponseCode();
            System.out.println(code);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
