package com.vpfinace.cloud_cat.utils;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class JsonUtils {
    /**
     * 传入Map返回RequestBody
     * @param map
     * @return
     */
    public static RequestBody getBody(Map map) {
        JSONObject jsonObject = new JSONObject(map);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
    }
}
