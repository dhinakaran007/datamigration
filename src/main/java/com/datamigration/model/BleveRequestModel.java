package com.datamigration.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BleveRequestModel {
    private String indexName;
    private String id;
    private List<String> fields;
    private Integer pageSize;
    private Integer pageIndex;
    private List<String> sort;
    private Double lat;
    private Double lon;
    private String distance;
    private Boolean useMatchQuery;
    private Boolean isSortByDist;
    private List<Map<String, Object>> filters;
    private Boolean useOr;
    private Map<String, Object> body;

    public BleveRequestModel() {

    }

    public BleveRequestModel(String indexName, String id, List<String> fields, Integer pageSize, Integer pageIndex,
            List<String> sort, Double lat, Double lon, String distance, Boolean useMatchQuery, Boolean isSortByDist,
            List<Map<String, Object>> filters, Boolean useOr, Map<String, Object> body) {
        this.indexName = indexName;
        this.id = id;
        this.fields = fields;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.sort = sort;
        this.lat = lat;
        this.lon = lon;
        this.distance = distance;
        this.useMatchQuery = useMatchQuery;
        this.isSortByDist = isSortByDist;
        this.filters = filters;
        this.useOr = useOr;
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

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<String> getSort() {
        return sort;
    }

    public void setSort(List<String> sort) {
        this.sort = sort;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Boolean getUseMatchQuery() {
        return useMatchQuery;
    }

    public void setUseMatchQuery(Boolean useMatchQuery) {
        this.useMatchQuery = useMatchQuery;
    }

    public Boolean getIsSortByDist() {
        return isSortByDist;
    }

    public void setIsSortByDist(Boolean isSortByDist) {
        this.isSortByDist = isSortByDist;
    }

    public List<Map<String, Object>> getFilters() {
        return filters;
    }

    public void setFilters(List<Map<String, Object>> filters) {
        this.filters = filters;
    }

    public Boolean getUseOr() {
        return useOr;
    }

    public void setUseOr(Boolean useOr) {
        this.useOr = useOr;
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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BleveRequestModel fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, BleveRequestModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
