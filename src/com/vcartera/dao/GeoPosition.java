package com.vcartera.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by Vitalie on 9/8/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoPosition {

    private static HashMap<String, Object> headers = new HashMap<>();
    private HashMap<String, Object> data = new HashMap<>();

    public void setLongitude(double longitude) {
        data.put("longitude", longitude);
        headers.put("longitude", longitude);
    }

    public void setLatitude(double latitude) {
        data.put("latitude", latitude);
        headers.put("latitude", latitude);
    }

    public static String header() {
        StringJoiner result = new StringJoiner(",");
        for (String key : headers.keySet()) {
            result.add(key);
        }

        return result.toString();
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(",");
        for (String key : headers.keySet()) {
            result.add((data.get(key) != null) ? data.get(key).toString() : "");
        }
        return result.toString();
    }
}
