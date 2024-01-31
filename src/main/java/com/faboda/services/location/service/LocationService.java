package com.faboda.services.location.service;


import com.faboda.services.location.dto.*;

public interface LocationService {

    CDeviceLocationResponse retrieveDeviceLocation(CDeviceLocationRequest cDeviceLocationRequest);
    CDeviceLocationVerificationResponse verifyDeviceLocation(CDeviceLocationVerificationRequest cDeviceLocationVerificationRequest);




}
