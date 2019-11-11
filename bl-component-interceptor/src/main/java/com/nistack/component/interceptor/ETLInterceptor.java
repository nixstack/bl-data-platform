package com.nistack.component.interceptor;

import com.nistack.component.interceptor.util.LogUtil;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 1、获取数据
        byte[] body = event.getBody();

        String log = new String(body, Charset.forName("UTF-8"));

        // 2、校验事件日志
        if (log.contains("startup")) {
            if (LogUtil.validateStartup(log)) {
                return event;
            }
        } else {
            if (LogUtil.validateEvent(log)) {
                // 校验通过
                return event;
            }
        }

        return null;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> interceptors = new ArrayList<>();

        for (Event event: events) {
            Event intercept = intercept(event);

            if (intercept != null) {
                interceptors.add(intercept);
            }
        }
        // 返回拦截到的数据
        return interceptors;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
