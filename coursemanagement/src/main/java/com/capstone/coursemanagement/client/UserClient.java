package com.capstone.coursemanagement.client;

import com.capstone.coursemanagement.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="user-service", fallback=UserClientFallback.class)
public interface UserClient {
    @GetMapping("/api/users/{id}")
    UserDTO getUserById(@PathVariable("id") UUID id);
    
//    @GetMapping("/fallback")
//     String fallbackMethod();
}
