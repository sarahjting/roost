package com.sarahjting.roost;

import com.sarahjting.roost.user.User;
import com.sarahjting.roost.user.UserDto;
import com.sarahjting.roost.user.services.UserCreator;
import com.sarahjting.roost.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class RoostApplication implements CommandLineRunner {
	protected final Logger LOG = LoggerFactory.getLogger(RoostApplication.class);

	@Autowired
	UserService userService;

	@Autowired
	UserCreator userCreator;

	public static void main(String[] args) {
		SpringApplication.run(RoostApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userService.countAll() == 0) {
			userCreator.execute(new UserDto("admin@example.com", "password!!!123"));
		}
	}
}
