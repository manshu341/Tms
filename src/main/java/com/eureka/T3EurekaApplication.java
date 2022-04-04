package com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * @author Ashish
 * 
 */
@EnableEurekaServer
@SpringBootApplication
public class T3EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(T3EurekaApplication.class, args);
	}

}
