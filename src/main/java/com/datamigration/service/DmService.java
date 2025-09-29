package com.datamigration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamigration.controller.BleveController;
import com.datamigration.model.BleveApiResponseModel;
import com.datamigration.model.BleveInsertApiModel;
import com.datamigration.model.BleveRequestModel;

@Service
public class DmService {
    @Autowired 
    BleveController bleveController;
    public BleveApiResponseModel getDataFromBleve(BleveRequestModel bleveRequest) {
        ResponseEntity<BleveApiResponseModel> responseEntity = bleveController.getDataFromBleveIndex(bleveRequest);
        return responseEntity.getBody();
    }

    public ResponseEntity<String> insertDataToBleve(BleveInsertApiModel insertApiModel) {
        return bleveController.insertToBleve(insertApiModel);
    }
}
