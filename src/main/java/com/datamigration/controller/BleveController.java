package com.datamigration.controller;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datamigration.config.service.ConfigLoader;
import com.datamigration.model.BleveApiResponseModel;
import com.datamigration.model.BleveInsertApiModel;
import com.datamigration.model.BleveRequestModel;

@RestController
public class BleveController {
    
    static final String BASE_URL;

    static {
        String tempUrl;
        try {
            tempUrl = ConfigLoader.returnUrl("E:/datamigration/src/main/java/com/datamigration/cache/config.json");
        } catch (Exception e) {
            e.printStackTrace();
            tempUrl = "";
        }

        BASE_URL = tempUrl;
    }

    @GetMapping("/api/getlatlong?address={addressString}")
    public static ResponseEntity<String> testReqEntity(@RequestParam String addressString) {
        try {
            String encodeString = URLEncoder.encode(addressString, "UTF-8");
            String url = BASE_URL + "/api/getlatlong?address=" + encodeString;
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.status(response.statusCode()).body(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/api/bleve/search")
    public ResponseEntity<BleveApiResponseModel> getDataFromBleveIndex(BleveRequestModel requestModel) {
        try {
            final String url = BASE_URL + "/api/bleve/search";
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(requestModel.toJson()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            BleveApiResponseModel responseModel = BleveApiResponseModel.fromJson(response.body());
            return ResponseEntity.status(response.statusCode()).body(responseModel);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/api/bleve/index")
    public ResponseEntity<String> insertToBleve(BleveInsertApiModel insertApiModel) {
        try {
            final String url = BASE_URL + "/api/bleve/index";
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(insertApiModel.toJson()))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return ResponseEntity.status(response.statusCode()).body(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
