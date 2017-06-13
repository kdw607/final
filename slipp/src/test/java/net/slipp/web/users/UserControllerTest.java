package net.slipp.web.users;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import net.slipp.dao.users.UserDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(MockitoJUnitRunner.class)8
public class UserControllerTest {
	
	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(userController)
						.alwaysExpect(status().isMovedTemporarily()).build();
	}

	@Test
	public void createWhenValid() throws Exception {
		this.mockMvc.perform(
					post("/users")
					.param("userId", "javajigi")
					.param("password", "password")
					.param("name", "�ڹ�����")
					.param("email", ""))
				.andDo(print())
				.andExpect(status().isMovedTemporarily())
				.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void createWhenInvalid() throws Exception {
		this.mockMvc.perform(
				post("/users")
				.param("userId", "javajigi")
				.param("password", "password")
				.param("name", "�ڹ�����")
				.param("email", "javajigi"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("users/form"));
	}
}