package com.faboda.services.location.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "locations")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@SuperBuilder
public class RetrievalLocationRequest {

    @Id
    private String id;
    private Device device;
    private int maxAge;

}
