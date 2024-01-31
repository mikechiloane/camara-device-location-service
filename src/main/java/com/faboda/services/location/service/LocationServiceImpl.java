package com.faboda.services.location.service;

import com.faboda.services.location.dto.*;
import com.faboda.services.location.feign.DeviceClient;
import com.faboda.services.location.feign.UserClient;
import com.faboda.services.location.models.User;
import com.faboda.services.location.models.fields.Area;
import com.faboda.services.location.models.fields.VerificationResults;
import com.faboda.services.location.util.CValidations;
import com.faboda.services.location.util.GeoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final UserClient userClient;
    private final DeviceClient deviceClient;

    @Override
    public CDeviceLocationResponse retrieveDeviceLocation(CDeviceLocationRequest cDeviceLocationRequest) {

        User user = userClient.getUserIdByEmail(cDeviceLocationRequest.getUserEmail());
        DeviceDto deviceDto = deviceClient.getDeviceByUserId(user.getId());
        CValidations.validateDevice(cDeviceLocationRequest.getDevice(), deviceDto.toDevice());
        return CDeviceLocationResponse.builder()
                .lastLocationTime(deviceDto.getLocation().getLastLocationTime())
                .area(Area.builder().areaType(deviceDto.getLocation().getLocationType())
                        .center(deviceDto.getLocation().getPoint())
                        .radius(100)
                        .build())
                .build();

    }

    @Override
    public CDeviceLocationVerificationResponse verifyDeviceLocation(CDeviceLocationVerificationRequest cDeviceLocationVerificationRequest) {
        User user = userClient.getUserIdByEmail(cDeviceLocationVerificationRequest.getUserEmail());
        DeviceDto device = deviceClient.getDeviceByUserId(user.getId());
        boolean isDeviceInArea = GeoUtils.isPointWithinRadius(device.getLocation().getPoint(), cDeviceLocationVerificationRequest.getArea().getCenter(), cDeviceLocationVerificationRequest.getArea().getRadius());

        return CDeviceLocationVerificationResponse.builder()
                .verificationResult(isDeviceInArea ? VerificationResults.TRUE : VerificationResults.FALSE)
                .build();

    }
}
