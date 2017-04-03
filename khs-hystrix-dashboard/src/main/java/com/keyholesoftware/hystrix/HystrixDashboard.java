package com.keyholesoftware.hystrix;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
//@EnableTurbine
public class HystrixDashboard {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HystrixDashboard.class, args);
    }
}