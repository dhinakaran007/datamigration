package com.datamigration.config.service;

import java.io.File;
import java.io.IOException;


import com.datamigration.config.model.LocaleConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigLoader {
    public static LocaleConfig loadConfig(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), LocaleConfig.class);
    }

    public static String returnUrl(String filePath) throws IOException{
        try {
            LocaleConfig config = ConfigLoader.loadConfig(filePath);
            return config.getBleveUrl();
        } catch (IOException e) {
            throw new IOException("Error loading configuration: " + e.getMessage());
        }
    }
}
