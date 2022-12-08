package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Hello world!");
            URL url = new URL("http://localhost:8080/user?name=James");

            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection(); // This already connects.
            getConnection.setRequestMethod("GET");
            getConnection.connect(); // This will do nothing.

            int code = getConnection.getResponseCode();
            /*if (code > 199 && code < 300) { // If POST, PUT or DELETE, only the error code is important to us. It should tell us how our request fared.
                System.out.println("Post successful!");
            }
            else {
                System.out.println("Post unsuccessful.");
                System.out.println("Error code: " + code);
            }*/
            if (code > 199 && code < 300) {
                BufferedReader in = new BufferedReader(new InputStreamReader(getConnection.getInputStream())); // This is the reader that will read the JSON.
                String inputLine;
                StringBuilder jsonString = new StringBuilder();

                while((inputLine = in.readLine()) != null) { // I put the next line in inputLine. If inputLine is null after that, we've reached the end.
                    jsonString.append(inputLine); // If not at the end, add to the string that contains the JSON.
                }
                in.close();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(String.valueOf(jsonString));
                System.out.println("Name: " + jsonObject.get("name"));
                System.out.println("Password: " + jsonObject.get("password"));
                System.out.println("Email: " + jsonObject.get("email"));
                System.out.println("Age: " + jsonObject.get("age"));
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        String[] responseBody = {""};

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("http://localhost:8080/user?name=Joe");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(stringHttpResponse -> stringHttpResponse.body())
                .thenApply((text) -> responseBody[0] = text).join();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(responseBody[0]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Name: " + jsonObject.get("name"));
        System.out.println("Password: " + jsonObject.get("password"));
        System.out.println("Email: " + jsonObject.get("email"));
        System.out.println("Age: " + jsonObject.get("age"));
    }
}