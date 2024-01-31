package com.faboda.services.location.feign;


import com.faboda.services.location.dto.DeviceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "device-service", url = "http://localhost:8084/api/v1/device")
public interface DeviceClient {

    @GetMapping("/getByUserId/{userId}")
    DeviceDto getDeviceByUserId(@PathVariable String userId);

}
