package com.example.URLShortener;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
        String urlStart = "http://localhost:8080/urls/full/"; // Modify the URL as needed

        // Number of requests to send
        int numRequests = 1000;

        for (int i = 0; i < numRequests; i++) {
            try {
                String url = urlStart + i + "a.com";
                URI uri = new URI(url);
                URL urlObj  = uri.toURL();
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // Set request headers if needed
                // connection.setRequestProperty("Content-Type", "application/json");

                // Set the request body, if any (e.g., JSON data)
                // String jsonInput = "Your JSON data";
                // try (OutputStream os = connection.getOutputStream()) {
                //     byte[] input = jsonInput.getBytes("utf-8");
                //     os.write(input, 0, input.length);
                // }

                int responseCode = connection.getResponseCode();

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    if (responseCode == 200) {
                        System.out.println("Request " + (i + 1) + " successful");
                    } else {
                        System.out.println("Request " + (i + 1) + " failed with status code " + responseCode);
                    }
                }
                connection.disconnect();
            } catch (IOException e) {
                System.out.println("Request " + (i + 1) + " failed: " + e.getMessage());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All POST requests sent.");
    }

}
