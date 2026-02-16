package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home Controller - Handles home page requests
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ApiResponse home() {
        return new ApiResponse("success", "Welcome to Somu Enterprises Backend API", null);
    }

    @GetMapping("/api/health")
    public ApiResponse health() {
        return new ApiResponse("success", "API is running", null);
    }

    /**
     * Simple API Response wrapper
     */
    public static class ApiResponse {
        private String status;
        private String message;
        private Object data;

        public ApiResponse(String status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}

