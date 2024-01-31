package com.faboda.services.location.service;

import com.faboda.services.location.dto.CDeviceLocationRequest;
import com.faboda.services.location.dto.CDeviceLocationResponse;
import com.faboda.services.location.dto.CDeviceLocationVerificationRequest;
import com.faboda.services.location.dto.DeviceDto;
import com.faboda.services.location.exceptions.InvalidArgumentException;
import com.faboda.services.location.exceptions.NotFoundException;
import com.faboda.services.location.feign.DeviceClient;
import com.faboda.services.location.feign.UserClient;
import com.faboda.services.location.models.Device;
import com.faboda.services.location.models.fields.Area;
import com.faboda.services.location.models.fields.Location;
import com.faboda.services.location.models.fields.Point;
import org.apache.kafka.common.utils.SystemTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @MockBean
    private DeviceClient deviceClient;

    @MockBean
    private UserClient userClient;


    @Test
    public void testGetDeviceLocation() {

        CDeviceLocationRequest cDeviceLocationRequest =  CDeviceLocationRequest.builder()
                .userEmail("test@test.com")
                .device(Device.builder().phoneNumber("0702468275").networkAccessIdentifier("test@test.com").build())
                .maxAge(100)
                .build();

        when(deviceClient.getDeviceByUserId(any())).thenReturn(getTestDeviceDto());
        when(userClient.getUserIdByEmail(any())).thenReturn(getTestUser());

        CDeviceLocationResponse cDeviceLocationResponse = locationService.retrieveDeviceLocation(cDeviceLocationRequest);
        assert cDeviceLocationResponse != null;
        assert  cDeviceLocationResponse.getArea() != null;
        assert  cDeviceLocationResponse.getLastLocationTime() != null;
    }

    @Test
    public void testVerifyDeviceLocation() {
        when(deviceClient.getDeviceByUserId(any())).thenReturn(getTestDeviceDto());
        when(userClient.getUserIdByEmail(any())).thenReturn(getTestUser());

        DeviceDto deviceDto = deviceClient.getDeviceByUserId("654ce470684a710b232dbc26");
        assert deviceDto != null;

        Device device = deviceDto.toDevice();
        CDeviceLocationVerificationRequest cDeviceLocationVerificationRequest = CDeviceLocationVerificationRequest.builder()
                .userEmail("test@test.com")
                .device(device)
                .maxAge(100)
                .area(Area.builder().areaType("Circle").center(device.getLocation().getPoint()).radius(100).build())
                        .build();

        assert locationService.verifyDeviceLocation(cDeviceLocationVerificationRequest) != null;
        cDeviceLocationVerificationRequest.setUserEmail("test@tes.com");
        when(userClient.getUserIdByEmail(any())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class,()->locationService.verifyDeviceLocation(cDeviceLocationVerificationRequest));
        device.setLocation(null);
        cDeviceLocationVerificationRequest.setArea(null);
        assertThrows(InvalidArgumentException.class,()->locationService.verifyDeviceLocation(cDeviceLocationVerificationRequest));
    }

    private static DeviceDto getTestDeviceDto() {

        Location location = Location.builder()
                .locationType("Circle")
                .lastLocationTime("now")
                .point(new Point(10,10))
                .build();

        return DeviceDto.builder()
                .id("654ce470684a710b232dbc26")
                .userId("654ce470684a710b232dbc26")
                .location(location)
                .phoneNumber("0702468275")
                .networkAccessIdentifier("test@test.com")
                .ipv4Address(null)
                .ipv6Address(null)
                .build();

    }

    private static com.faboda.services.location.models.User getTestUser() {
        return com.faboda.services.location.models.User.builder()
                .id("654ce470684a710b232dbc26")
                .email("test@test.com")
                .phoneNumber("0702468275")
                .build();

    }

}
