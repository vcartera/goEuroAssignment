package com.vcartera.dao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

/**
 * Generic DAO for goEuro JSON objects
 * Created by Vitalie on 9/12/2016.
 */
public abstract class DataAccessObject {

    // holds all the available key:value pairs
    protected HashMap<String, Object> data = new HashMap<>();

    public static String header() {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        throw new NotImplementedException();
    }
}
