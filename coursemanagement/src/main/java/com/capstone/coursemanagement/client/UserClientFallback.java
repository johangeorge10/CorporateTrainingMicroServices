package com.capstone.coursemanagement.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.capstone.coursemanagement.dto.UserDTO;

@Component
public class UserClientFallback implements UserClient {

	@Override
	public UserDTO getUserById(UUID id) {
		// TODO Auto-generated method stub
		System.out.println("User service is currently unavailable");
		return new UserDTO("User service is currently unavailable");
	}
//	@Override
//	public String fallbackMethod() {
//		// TODO Auto-generated method stub
//		return "User service is currently unavailable";
//	}

}
