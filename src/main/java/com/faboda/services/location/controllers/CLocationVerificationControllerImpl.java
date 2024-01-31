package com.faboda.services.location.controllers;

import com.faboda.services.location.dto.CDeviceLocationVerificationRequest;
import com.faboda.services.location.dto.CDeviceLocationVerificationResponse;
import com.faboda.services.location.exceptions.InvalidArgumentException;
import com.faboda.services.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location-verification/v0")
public class CLocationVerificationControllerImpl implements CLocationVerificationController{

    private final LocationService locationService;

    @Override
    @PostMapping("/verify")
    public ResponseEntity<CDeviceLocationVerificationResponse> verifyDeviceLocation(@RequestBody  CDeviceLocationVerificationRequest cDeviceLocationVerificationRequest,@RequestHeader("X-User-Email") String email) throws InvalidArgumentException {
        cDeviceLocationVerificationRequest.setUserEmail(email);
        return ResponseEntity.ok(locationService.verifyDeviceLocation(cDeviceLocationVerificationRequest));
    }
}
