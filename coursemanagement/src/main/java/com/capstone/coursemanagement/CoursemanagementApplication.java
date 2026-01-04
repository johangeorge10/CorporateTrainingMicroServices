package com.capstone.coursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CoursemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursemanagementApplication.class, args);
	}

}
