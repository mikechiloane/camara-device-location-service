package com.faboda.services.location.controllers;

import com.faboda.services.location.dto.CDeviceLocationRequest;
import com.faboda.services.location.dto.CDeviceLocationResponse;
import org.springframework.http.ResponseEntity;

public interface CLocationRetrievalController {

        public ResponseEntity<CDeviceLocationResponse> retrieveDeviceLocation(CDeviceLocationRequest cDeviceLocationRequest, String email);
}
