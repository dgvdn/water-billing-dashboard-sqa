package com.example.waterbilling.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.waterbilling.model.Role;
import com.example.waterbilling.model.User;

@RunWith(SpringRunner.class)
@Transactional
@DataJpaTest
class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	public void findByUsername_shouldReturnUser() {
		// Given
		User user = new User();
		user.setUsername("john.doe");
		user.setPassword("password");
		user.setFullName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setRole(Role.USER);
		userRepository.save(user);

		// When
		User foundUser = userRepository.findByUsername("john.doe");

		// Then
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
		assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
		assertThat(foundUser.getFullName()).isEqualTo(user.getFullName());
		assertThat(foundUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(foundUser.getRole()).isEqualTo(user.getRole());
	}

	@Test
	public void findByUsername_shouldReturnNull_whenUserNotFound() {
		// Given
		User user = new User();
		user.setUsername("john.doe");
		user.setPassword("password");
		user.setFullName("John Doe");
		user.setEmail("john.doe@example.com");
		user.setRole(Role.USER);
		userRepository.save(user);

		// When
		User foundUser = userRepository.findByUsername("jane.doe");

		// Then
		assertThat(foundUser).isNull();
	}
}
