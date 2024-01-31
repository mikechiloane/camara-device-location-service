package com.faboda.services.location.models.fields;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class Circle{
    private Point center;
    private double radius;
}
