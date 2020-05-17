package com.example.demo;

import com.example.demo.cacheannotation.CacheTTL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRedisCustomCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisCustomCacheApplication.class, args);
	}

	// Demo method call to store the return objects to cache for 5 minutes

	/*@CacheTTL(ttlMinutes = 5)
	public List<User> getUsers() {
		List<User> users = dbService.getAllUsersFromDB();
		return users;
	}*/
}
