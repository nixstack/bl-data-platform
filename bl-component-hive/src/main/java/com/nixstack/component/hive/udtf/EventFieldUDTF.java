package com.nixstack.component.hive.udtf;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class EventFieldUDTF extends GenericUDTF {
    /** @deprecated */
    @Deprecated
    public StructObjectInspector initialize(ObjectInspector[] argOIs) throws UDFArgumentException {
        List<String> fieldNames = new ArrayList();
        List<ObjectInspector> fieldTypes = new ArrayList();

        fieldNames.add("event_name");
        fieldTypes.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        fieldNames.add("event_json");
        fieldTypes.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldTypes);
    }

    public void process(Object[] objects) throws HiveException {
        // 获取数据events
        String event = objects[0].toString();

        if (StringUtils.isBlank(event)) {
            return;
        } else {
            try {
                JSONArray jsonArray = new JSONArray(event);

                if (jsonArray == null) {
                    return;
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    String[] results = new String[2];

                    // 获取事件名称
                    results[0]= jsonArray.getJSONObject(i).getString("type");
                    results[1] =jsonArray.getString(i);

                    // 写结果
                    forward(results);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() throws HiveException {

    }
}
