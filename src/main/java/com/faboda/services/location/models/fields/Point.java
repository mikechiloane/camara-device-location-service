package com.faboda.services.location.models.fields;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {
    private double latitude;
    private double longitude;
}
