package com.datamigration.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.datamigration.model.BleveInsertApiModel;
import com.datamigration.model.locale.LocaleModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CsvUploadService {
    private final DmService dmService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CsvUploadService(DmService dmService) {
        this.dmService = dmService;
    }

    public void upload(String csvFilePath) throws Exception {
        CsvParserService parserService = new CsvParserService();
        List<LocaleModel> localeModels = parserService.parseCsv(csvFilePath);

        List<CompletableFuture<Void>> futures = localeModels.stream()
                .map(model -> CompletableFuture.runAsync(() -> uploadCsvDataToBleve(model)))
                .toList();
                
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("All data uploaded to Bleve.");
    }

    public void uploadCsvDataToBleve(LocaleModel localeModel) {
        try {
            BleveInsertApiModel insertApiModel = new BleveInsertApiModel();
            insertApiModel.setIndexName("locale2");
            insertApiModel.setId(localeModel.getId());
            Map<String, Object> bodyMap = objectMapper.convertValue(localeModel, Map.class);
            insertApiModel.setBody(bodyMap);

            dmService.insertDataToBleve(insertApiModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
