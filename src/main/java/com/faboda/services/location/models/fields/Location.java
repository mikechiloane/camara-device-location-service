package com.faboda.services.location.models.fields;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {
    private String lastLocationTime;
    private Point point;
    private String locationType;
}
