package com.nixstack.component.hive.udf;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonFieldUDF extends UDF {
    public String evaluate(String line, String jsonkeysStr){
        StringBuffer stringBuffer = new StringBuffer();

        String[] jsonkeys = jsonkeysStr.split(",");

        // line: 服务器时间 | json
        if (line == null) {
            return "";
        }
        String[] logContents = line.split("\\|");

        if (logContents.length != 2 || StringUtils.isBlank(logContents[1])) {
            return "";
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(logContents[1]);

            JSONObject cm = jsonObject.getJSONObject("common");

            for (int i = 0; i < jsonkeys.length; i++) {
                String jsonkey = jsonkeys[i];

                if(cm.has(jsonkey)){
                    stringBuffer.append(cm.getString(jsonkey)).append("\t");
                }else {
                    stringBuffer.append("\t");
                }
            }

            stringBuffer.append(jsonObject.getString("event")).append("\t");
            stringBuffer.append(logContents[0]).append("\t");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}
