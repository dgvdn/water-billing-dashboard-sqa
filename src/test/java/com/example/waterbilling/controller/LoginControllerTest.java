package com.example.waterbilling.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLoginWithInvalidCredentials() throws Exception {
		mockMvc.perform(formLogin("/login").user("invalidusername").password("invalidpassword"))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/login?error")).andExpect(unauthenticated());
	}

	@Test
	public void testLoginWithValidUsernameAndPassword() throws Exception {
		mockMvc.perform(post("/login").param("username", "admin").param("password", "password"))
				.andExpect(status().isFound()).andExpect(redirectedUrl("/"))
				.andExpect(authenticated().withUsername("admin"));
	}

	@Test
	public void testLogout() throws Exception {
		// Gửi một yêu cầu GET đến URL /logout
		mockMvc.perform(get("/logout")).andExpect(status().is3xxRedirection()) // Kiểm tra xem trang đã được chuyển
																				// hướng hay chưa
				.andExpect(redirectedUrl("/login?logout")); // Kiểm tra xem trang đã được chuyển hướng đến trang đăng
															// nhập hay chưa
	}

}
