package com.faboda.services.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class LocationDto {
    public  String username;
    public  double lng;
    public  double lat;
    public String name;
}
