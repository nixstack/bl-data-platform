package com.nixstack.component.hive.udf;

import org.junit.Test;

public class CommonFieldUDFTest {
    @Test
    public void evaluate() {
        String line = "1572221223907|{\"common\":{\"area\":\"shanghai\",\"os\":\"android\",\"channel\":\"website\",\"mId\":\"1784\",\"versionName\":\"v1.0.9\",\"resolution\":\"640*1136\",\"versionCode\":\"13\",\"network\":\"WIFI\",\"uId\":\"63068\",\"phoneModel\":\"huawei-22\",\"phoneBrand\":\"Huawei\",\"time\":\"1572122844780\",\"lang\":\"en_us\"},\"source\":\"app\",\"event\":[{\"time\":\"1572203208315\",\"type\":\"page_list\",\"map\":{\"loadingTime\":\"14\",\"loadingWay\":\"2\",\"errorCode\":\"\",\"loadingType\":\"3\",\"status\":\"2\"}}]}";
        String output = new CommonFieldUDF().evaluate(line, "mId,uId,versionCode,versionName,lang,channel,os,area,phoneModel,phoneBrand,sdkVersion,resolution,time,network,longitude,latitude, time");

        System.out.println(output);
    }
}
