package com.vcartera.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Data Access Object for goEuro Location object structure
 * Created by Vitalie on 9/9/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location extends DataAccessObject {

    // static member holds all the available unique keys
    private static HashMap<String, Object> headers = new HashMap<>();

    @JsonProperty("_id")
    public void setId(long id) {
        data.put("_id", id);
        headers.put("_id", id);
    }

    public void setKey(int key) {
        data.put("key", key);
        headers.put("key", key);
    }

    public void setName(String name) {
        data.put("name", name);
        headers.put("name", name);
    }

    public void setFullName(String fullName) {
        fullName = (fullName == null) ? "" : fullName;
        fullName = fullName.replace(",", ""); // strip possible commas
        data.put("fullName", fullName);
        headers.put("fullName", fullName);
    }

    @JsonProperty("iata_airport_code")
    public void setIataAirportCode(String iataAirportCode) {
        iataAirportCode = (iataAirportCode == null) ? "" : iataAirportCode;
        data.put("iata_airport_code", iataAirportCode);
        headers.put("iata_airport_code", iataAirportCode);
    }

    @JsonProperty("type")
    public void setType(String type) {
        type = (type == null) ? "" : type;
        data.put("type", type);
        headers.put("type", type);
    }

    public void setCountry(String country) {
        country = (country == null) ? "" : country;
        data.put("country", country);
        headers.put("country", country);
    }

    @JsonProperty("geo_position")
    public void setGeoPosition(GeoPosition geoPosition) {
        data.put("geo_position", geoPosition);
        headers.put("geo_position", geoPosition);
    }

    public void setLocationId(long locationId) {
        data.put("locationId", locationId);
        headers.put("locationId", locationId);
    }

    public void setCountryId(int countryId) {
        data.put("countryId", countryId);
        headers.put("countryId", countryId);
    }

    public void setInEurope(boolean inEurope) {
        data.put("inEurope", inEurope);
        headers.put("inEurope", inEurope);
    }

    public void setCountryCode(String countryCode) {
        countryCode = (countryCode == null) ? "" : countryCode;
        data.put("countryCode", countryCode);
        headers.put("countryCode", countryCode);
    }

    public void setCoreCountry(boolean coreCountry) {
        data.put("coreCountry", coreCountry);
        headers.put("coreCountry", coreCountry);
    }

    public void setDistance(long distance) {
        data.put("distance", distance);
        headers.put("distance", distance);
    }

    public void setNames(Names names) {
        data.put("names", names);
        headers.put("names", names);
    }

    /**
     * Joins all the available keys to form the header
     *
     * @return headers string
     */
    public static String header() {
        StringJoiner result = new StringJoiner(",");
        for (String key : headers.keySet()) {
            if (headers.get(key) instanceof Names) {
                result.add(Names.header());
            } else if (headers.get(key) instanceof GeoPosition) {
                result.add(GeoPosition.header());
            } else {
                result.add(key);
            }
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
