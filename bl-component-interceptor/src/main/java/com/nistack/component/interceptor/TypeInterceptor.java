package com.nistack.component.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypeInterceptor  implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // event放到header
        // 1、获取body数据
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UTF-8"));

        // 获取header
        Map<String, String> headers = event.getHeaders();

        // 设置heaer，用于后面kafaka,flume接收和消费
        if (log.contains("startup")) {
            headers.put("topic", "topic_startup");
        } else {
            headers.put("topic", "topic_event");
        }
        
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        ArrayList<Event> interceptors = new ArrayList<>();
        for (Event event : events) {
            Event intercept = intercept(event);
            interceptors.add(intercept);
        }
        return interceptors;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {

        @Override
        public Interceptor build() {

            return new TypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
