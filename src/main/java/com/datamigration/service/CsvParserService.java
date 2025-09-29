package com.datamigration.service;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.datamigration.model.locale.LocaleModel;

public class CsvParserService {
    public List<LocaleModel> parseCsv (String filePath) throws Exception {
        List<LocaleModel> localeModels = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())
        ) {
            for (CSVRecord record : csvParser) {
                LocaleModel localeModel = new LocaleModel();
                localeModel.setId(record.get("id"));
                localeModel.setEntityId(record.get("entityId"));
                localeModel.setLocale(record.get("locale"));
                localeModel.setK(record.get("k"));
                localeModel.setV(record.get("v"));
                localeModel.setaDt(Double.valueOf(record.get("aDt")));
                localeModels.add(localeModel);
            }
        }

        return localeModels;
    }
}
