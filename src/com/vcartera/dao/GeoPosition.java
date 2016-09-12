package com.vcartera.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Data Access Object for goEuro JSON geo-position object
 * Created by Vitalie on 9/8/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoPosition extends DataAccessObject {

    // static member holds all the available unique keys
    private static HashMap<String, Object> headers = new HashMap<>();

    public void setLongitude(double longitude) {
        data.put("longitude", longitude);
        headers.put("longitude", longitude);
    }

    public void setLatitude(double latitude) {
        data.put("latitude", latitude);
        headers.put("latitude", latitude);
    }

    /**
     * Joins all the available keys to form the header
     *
     * @return headers string
     */
    public static String header() {
        StringJoiner result = new StringJoiner(",");
        for (String key : headers.keySet()) {
            result.add(key);
        }

        return result.toString();
    }

    /**
     * Joins all fields with commas
     *
     * @return concatenated row separated by commas
     */
    public String toString() {
        StringJoiner result = new StringJoiner(",");
        for (String key : headers.keySet()) {
            String val = (data.get(key) != null) ? data.get(key).toString() : "";
            result.add(val);
        }
        return result.toString();
    }
}
