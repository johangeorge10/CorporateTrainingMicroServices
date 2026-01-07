package com.capstone.enrollment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "course-management-service", url = "http://localhost:8082" , fallback=CourseFeignClientFallback.class)
public interface CourseFeignClient {

    @GetMapping("/api/courses/{courseId}")
    CourseResponse checkCourseExists(@PathVariable UUID courseId);

    
    
    class CourseResponse {
        public UUID id;
        public Boolean active;
        
        //for fall back method
        public String message;
        public CourseResponse(String message){
        	this.message=message;
        }
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
    }
}
