package com.faboda.services.location.dto;

import com.faboda.services.location.models.fields.Area;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CDeviceLocationResponse {

    private String lastLocationTime;
    private Area area;
}
