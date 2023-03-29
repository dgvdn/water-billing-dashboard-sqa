package com.example.waterbilling.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.waterbilling.model.Role;
import com.example.waterbilling.model.User;
import com.example.waterbilling.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByUsername("admin") == null) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("password"));
			admin.setFullName("Admin");
			admin.setEmail("admin@example.com");
			admin.setRole(Role.ADMIN);
			userRepository.save(admin);
		}
	}

}
