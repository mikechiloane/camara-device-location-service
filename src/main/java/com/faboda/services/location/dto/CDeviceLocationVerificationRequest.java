package com.faboda.services.location.dto;


import com.faboda.services.location.models.Device;
import com.faboda.services.location.models.fields.Area;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CDeviceLocationVerificationRequest {

    private Device device;
    private Area area;
    private int maxAge;
    private String userEmail;
}
