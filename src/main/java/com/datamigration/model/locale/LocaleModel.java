package com.datamigration.model.locale;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LocaleModel {
    private String id;
    private String entityId;
    private String locale;
    private String k;
    private String v;
    private Double aDt;

    public LocaleModel() {

    }

    public LocaleModel(String id, String entityId, String locale, String k, String v, Double aDt) {
        this.id = id;
        this.entityId = entityId;
        this.locale = locale;
        this.k = k;
        this.v = v;
        this.aDt = aDt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public Double getaDt() {
        return aDt;
    }

    public void setaDt(Double aDt) {
        this.aDt = aDt;
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

    public static LocaleModel fromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, LocaleModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
