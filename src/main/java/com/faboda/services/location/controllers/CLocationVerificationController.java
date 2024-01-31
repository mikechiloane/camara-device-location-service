package com.faboda.services.location.controllers;


import com.faboda.services.location.dto.CDeviceLocationVerificationRequest;
import com.faboda.services.location.dto.CDeviceLocationVerificationResponse;
import org.springframework.http.ResponseEntity;

public interface CLocationVerificationController {

    public ResponseEntity<CDeviceLocationVerificationResponse> verifyDeviceLocation(CDeviceLocationVerificationRequest cDeviceLocationVerificationRequest, String email);
}
