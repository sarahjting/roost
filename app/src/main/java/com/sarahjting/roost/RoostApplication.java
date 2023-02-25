package com.sarahjting.roost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@SpringBootApplication
@Configuration
public class RoostApplication {
	protected final Logger LOG = LoggerFactory.getLogger(RoostApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RoostApplication.class, args);
	}
}
