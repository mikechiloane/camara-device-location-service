package com.faboda.services.location.util;

import com.faboda.services.location.exceptions.InvalidArgumentException;
import com.faboda.services.location.exceptions.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new InvalidArgumentException();
        } else if (responseStatus.is4xxClientError()) {
            return new NotFoundException("Resource not found");
        } else {
            return new Exception("Generic exception");
        }
    }
}