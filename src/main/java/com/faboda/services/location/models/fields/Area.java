package com.faboda.services.location.models.fields;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Area {
    private String areaType;
    private Point center;
    private double radius;
}
