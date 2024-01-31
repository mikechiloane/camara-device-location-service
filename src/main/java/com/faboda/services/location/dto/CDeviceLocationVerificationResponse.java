package com.faboda.services.location.dto;

import com.faboda.services.location.models.fields.VerificationResults;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CDeviceLocationVerificationResponse {

    private VerificationResults verificationResult;
    private String lastLocationTime;

}


