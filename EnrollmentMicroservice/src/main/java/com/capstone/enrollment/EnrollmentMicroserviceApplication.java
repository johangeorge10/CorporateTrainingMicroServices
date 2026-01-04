package com.capstone.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EnrollmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentMicroserviceApplication.class, args);
	}

}
