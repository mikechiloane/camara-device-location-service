package com.faboda.services.location.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Boolean isVerified;
    private String phoneNumber;
    private Date dateCreated;
    private Date dateUpdated;
    private String password;

}
