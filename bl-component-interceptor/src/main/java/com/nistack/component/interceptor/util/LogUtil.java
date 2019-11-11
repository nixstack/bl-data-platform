package com.nistack.component.interceptor.util;

import org.apache.commons.lang.math.NumberUtils;

public class LogUtil {
    public static boolean validateStartup(String log) {
        if (log == null){
            return false;
        }

        if (!log.trim().startsWith("{") || !log.trim().endsWith("}")){
            return false;
        }

        return true;
    }

    public static boolean validateEvent(String log) {
        if (log == null) {
            return false;
        }

        // timestamp|json
        String[] split = log.split("\\|");
        if (split.length != 2) {
            return false;
        }
        if (split[0].length() != 13 || !NumberUtils.isDigits(split[0])) {
            return false;
        }
        if (!split[1].trim().startsWith("{") || !split[1].trim().endsWith("}")) {
            return false;
        }

        // 只有为true才会拦截到数据
        return true;
    }
}
