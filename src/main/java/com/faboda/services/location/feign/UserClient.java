package com.faboda.services.location.feign;


import com.faboda.services.location.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8083/v1/user")
public interface UserClient {
    @GetMapping("/email/{email}")
    User getUserIdByEmail(@PathVariable String email);
}
