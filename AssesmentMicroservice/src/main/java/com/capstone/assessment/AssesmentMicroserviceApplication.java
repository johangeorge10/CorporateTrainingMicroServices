package com.capstone.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AssesmentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssesmentMicroserviceApplication.class, args);
	}

}
