package com.licenta.project.scheduler.tasks;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.apache.log4j.Logger;

import static globals.Constants.API_KEY;

public class TopHeadlines {

    private static final Logger logger = Logger.getLogger(TopHeadlines.class);

    public String getHeadlinesByCountry(String countryCode){

        logger.debug("Calling top-headlines in US api called");

        int totalChunks = getNumberOfPages("https://newsapi.org/v2/top-headlines?country=" + countryCode + "&apiKey=" + API_KEY);
        int chunk = 1;
        String response = "{\"articles\":[";
        while(chunk <= totalChunks){
            try {
                URL url = new URL("https://newsapi.org/v2/top-headlines?country=" + countryCode + "&apiKey=" + API_KEY + "&page=" + chunk);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if(conn.getResponseCode() != 200){
                    throw new RuntimeException("Failed : HTTP error code: " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String output;
                String str = null;
                while((output = br.readLine()) != null){
                    str = output;
                }

                if ((str.equals(null))) {
                    return null;
                } else {
                    String[] outArray = str.split(",");
                    outArray[2] = str.split(",")[2].substring(12);
                    String out = "";
                    for (int j = 2; j < outArray.length; j++) {
                        out += outArray[j] + ",";
                        if (j == outArray.length - 1) {
                            out = out.substring(0, out.length() - 3);
                        }
                    }
                    response = (response.equals("{\"articles\":[")) ? response + out : response + "," + out;
                }
            } catch (MalformedURLException e) {
                logger.error("Error at getting all US top-headlines");
                e.printStackTrace();
            } catch (IOException e) {
                logger.error("Error at getting all US top-headlines");
                e.printStackTrace();
            }
            chunk++;
        }

        return response + "]}";
    }


    public String getHeadlinesByCategory(String countryCode, String category){

        logger.debug("Calling top-headlines in US api on domain");

        int totalChunks = getNumberOfPages("https://newsapi.org/v2/top-headlines?country=" + countryCode + "&category=" + category + "&apiKey=" + API_KEY);
        int chunk = 1;
        String response = "{\"articles\":[";
        while(chunk <= totalChunks){
            try {
                URL url = new URL("https://newsapi.org/v2/top-headlines?country=" + countryCode + "&category=" + category + "&apiKey=" + API_KEY + "&page=" + chunk);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if(conn.getResponseCode() != 200){
                    throw new RuntimeException("Failed : HTTP error code: " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String output;
                String str = null;
                while((output = br.readLine()) != null){
                    str = output;
                }

                if ((str.equals(null))) {
                    return null;
                } else {
                    String[] outArray = str.split(",");
                    outArray[2] = str.split(",")[2].substring(12);
                    String out = "";
                    for (int j = 2; j < outArray.length; j++) {
                        out += outArray[j] + ",";
                        if (j == outArray.length - 1) {
                            out = out.substring(0, out.length() - 3);
                        }
                    }
                    response = (response.equals("{\"articles\":[")) ? response + out : response + "," + out;
                }
            } catch (MalformedURLException e) {
                logger.error("Error at getting all US top-headlines");
                e.printStackTrace();
            } catch (IOException e) {
                logger.error("Error at getting all US top-headlines");
                e.printStackTrace();
            }
            chunk++;
        }

        return response + "]}";
    }

    private int getNumberOfPages(String urlString){
        URL url = null;
        try {
//            url = new URL("https://newsapi.org/v2/top-headlines?country=" + countryCode + "&apiKey=" + API_KEY + "&page=1");
            url = new URL(urlString + "&page=1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if(conn.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            String str = null;
            while((output = br.readLine()) != null){
                str = output;
            }

            if(!str.equals(null)){
                String totalNumber = str.split(",")[1];
                float numberOfChuncks = Float.parseFloat(totalNumber.split(":")[1]);
                return ((numberOfChuncks/20) > ((int)numberOfChuncks/20)) ? ((int) numberOfChuncks/20 + 1) : ((int) numberOfChuncks/20);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

//    private int getNumberOfPages(String countryCode){
//        URL url = null;
//        try {
//            url = new URL("https://newsapi.org/v2/top-headlines?country=" + countryCode + "&apiKey=" + API_KEY + "&page=1");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/json");
//
//            if(conn.getResponseCode() != 200){
//                throw new RuntimeException("Failed : HTTP error code: " + conn.getResponseCode());
//            }
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String output;
//            String str = null;
//            while((output = br.readLine()) != null){
//                str = output;
//            }
//
//            if(!str.equals(null)){
//                String totalNumber = str.split(",")[1];
//                float numberOfChuncks = Float.parseFloat(totalNumber.split(":")[1]);
//                return ((numberOfChuncks/20) > ((int)numberOfChuncks/20)) ? ((int) numberOfChuncks/20 + 1) : ((int) numberOfChuncks/20);
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }
}
