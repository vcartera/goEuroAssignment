package com.vcartera;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcartera.dao.Location;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Main entry goEuro Assignment
 * Command line tool: takes city name as command line argument and produces a CSV formatted file.
 * <p>
 * Accesses an API endpoint and consumes specific JSON formatted data (as per assignment specs).
 * <p>
 * Created by Vitalie on 9/8/2016.
 */
public class GoEuroTest {

    private static final String ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en/";
    private static final String ARGUMENTS_ERROR = "City name is required.\n\rExample of usage: java -jar GoEuroTest.jar \"CITY_NAME\"";
    private static final String OUTPUT_ERROR = "Could not create output file";
    private static final String REQUEST_METHOD = "GET";
    private static final String SYSTEM_LINE_SEPARATOR = "line.separator";
    private static final String CSV_FILE_EXTENSION = ".csv";
    private static final String OUTPUT_MESSAGE = "Output file: ";

    public static void main(String[] args) throws Exception {

        // check command line arguments and prints error message
        if (args.length == 0) {
            System.out.println(ARGUMENTS_ERROR);
            System.exit(0);
        }

        String city = args[0];

        // Retrieve data, map JSON to DAO, parse and format
        String output = mapAndFormat(retrieveData(city));

        // Write to the output file
        writeCSV(output, city);
    }

    private static HttpURLConnection retrieveData(String city) throws Exception {
        // Access endpoint and retrieve JSON data
        URL url = new URL(ENDPOINT + city);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod(REQUEST_METHOD);
        return connection;
    }

    private static String mapAndFormat(HttpURLConnection connection) throws Exception {
        // Parse and map JSON object to DAO's
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ObjectMapper mapper = new ObjectMapper();
        List<Location> items = mapper.readValue(rd,
                new TypeReference<ArrayList<Location>>() {
                });

        rd.close();

        String output = "";
        String eol = System.getProperty(SYSTEM_LINE_SEPARATOR);

        // Grab headers..
        output += Location.header() + eol;

        // .. join and format data
        for (Location item : items) {
            output += item.toString() + eol;
        }

        return output;
    }

    private static void writeCSV(String output, String city) {
        try {
            File file = new File(city + CSV_FILE_EXTENSION);
            FileUtils.writeStringToFile(file, output);
            // Success
            System.out.println(OUTPUT_MESSAGE + file);
        } catch (IOException e) {
            // File could not be created
            System.out.println(OUTPUT_ERROR);

            e.printStackTrace();
        }
    }
}
