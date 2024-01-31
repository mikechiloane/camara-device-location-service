package com.faboda.services.location.controllers;

import com.faboda.services.location.dto.CDeviceLocationRequest;
import com.faboda.services.location.dto.CDeviceLocationResponse;
import com.faboda.services.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/location-retrieval/v0")
public class CLocationRetrievalControllerImpl implements CLocationRetrievalController{

    private final LocationService locationService;
    @Override
    @PostMapping("/retrieve")
    public ResponseEntity<CDeviceLocationResponse> retrieveDeviceLocation(@RequestBody CDeviceLocationRequest cDeviceLocationRequest, @RequestHeader("X-User-Email") String email) {
        cDeviceLocationRequest.setUserEmail(email);
        return ResponseEntity.ok(locationService.retrieveDeviceLocation(cDeviceLocationRequest));
    }
}
