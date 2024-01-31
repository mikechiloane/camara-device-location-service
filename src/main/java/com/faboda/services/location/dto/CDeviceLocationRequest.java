package com.faboda.services.location.dto;

import com.faboda.services.location.models.Device;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CDeviceLocationRequest {
    private String userEmail;
    private Device device;
    private int maxAge;
}
