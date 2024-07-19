package com.shawn.supervoting;

import com.shawn.supervoting.service.UserRoleService;
import com.shawn.supervoting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupervotingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SupervotingApplication.class, args);
	}

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;
	@Override
	public void run(String... args) throws Exception {
		userRoleService.initializerUserRoles();
		userService.initializeAdmin();
	}
}
