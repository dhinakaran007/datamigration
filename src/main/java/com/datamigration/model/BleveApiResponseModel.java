package com.datamigration.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BleveApiResponseModel {
    private Integer total;
    private List<Map<String, Object>> data;
    private Integer took;


    public BleveApiResponseModel() {

    }

    public BleveApiResponseModel(Integer total, List<Map<String, Object>> data, Integer took) {
        this.total = total;
        this.data = data;
        this.took = took;
    }


    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public List<Map<String, Object>> getData() {
        return data;
    }
    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
    public Integer getTook() {
        return took;
    }
    public void setTook(Integer took) {
        this.took = took;
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

    public static BleveApiResponseModel fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, BleveApiResponseModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
} 
