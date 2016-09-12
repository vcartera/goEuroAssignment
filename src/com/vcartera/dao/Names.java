package com.vcartera.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Data Access Object for goEuro JSON Names object
 * Created by Vitalie on 9/9/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Names extends DataAccessObject {

    // static member holds all the available unique keys
    private static HashMap<String, Object> headers = new HashMap<>();

    public void setPt(String pt) {
        pt = (pt == null) ? "" : pt;
        data.put("pt", pt);
        headers.put("pt", pt);
    }

    public void setRu(String ru) {
        ru = (ru == null) ? "" : ru;
        data.put("ru", ru);
        headers.put("ru", ru);
    }

    public void setIt(String it) {
        it = (it == null) ? "" : it;
        data.put("it", it);
        headers.put("it", it);
    }

    public void setIs(String is) {
        is = (is == null) ? "" : is;
        data.put("is", is);
        headers.put("is", is);
    }

    public void setFi(String fi) {
        fi = (fi == null) ? "" : fi;
        data.put("fi", fi);
        headers.put("fi", fi);
    }

    public void setEs(String es) {
        es = (es == null) ? "" : es;
        data.put("es", es);
        headers.put("es", es);
    }

    public void setZh(String zh) {
        zh = (zh == null) ? "" : zh;
        data.put("zh", zh);
        headers.put("zh", zh);
    }

    public void setCs(String cs) {
        cs = (cs == null) ? "" : cs;
        data.put("cs", cs);
        headers.put("cs", cs);
    }

    public void setCa(String ca) {
        ca = (ca == null) ? "" : ca;
        data.put("ca", ca);
        headers.put("ca", ca);
    }

    public void setNl(String nl) {
        nl = (nl == null) ? "" : nl;
        data.put("nl", nl);
        headers.put("nl", nl);
    }

    public void setDe(String de) {
        de = (de == null) ? "" : de;
        data.put("de", de);
        headers.put("de", de);
    }

    /**
     * Joins all the available keys to form the header
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
