package com.datamigration.model;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BleveInsertApiModel {
    private String indexName;
    private String id;
    private Map<String, Object> body;

    public BleveInsertApiModel() {

    }

    public BleveInsertApiModel(String indexName, String id, Map<String, Object> body) {
        this.indexName = indexName;
        this.id = id;
        this.body = body;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BleveInsertApiModel fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, BleveInsertApiModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
