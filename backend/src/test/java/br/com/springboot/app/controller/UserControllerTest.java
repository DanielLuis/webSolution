/**
 * 
 */
package br.com.springboot.app.controller;

import static br.com.springboot.app.support.ResponseBodyMatchers.responseBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.springboot.app.assembler.UserModelAssembler;
import br.com.springboot.app.domain.User;
import br.com.springboot.app.exceptions.UserNotFoundException;
import br.com.springboot.app.service.UserService;
import br.com.springboot.app.support.UserStatus;

/**
 * @author dvicente
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	public  MockMvc mockMvc;

	@MockBean
	private  UserService service;

	@Autowired
	private  ObjectMapper objectMapper;

	@Autowired
	private UserController controller;

	private static String URL_USERS = "/users/";

	private static final String URL_USERS_FIND_BY_ID = URL_USERS+"/findById/{id}";

	private static final String URL_USERS_CREATE = URL_USERS+"/create";

	private static final String URL_USERS_INACTIVATE = URL_USERS+"/inactivate/{id}";

	private static final String URL_USERS_ACTIVATE = URL_USERS+"/activate/{id}";


	public String  obterJson(User user) {
		String json = null;

		try {
			json = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


		return json;
	}
	private User getUser(Long id) {
		return getUsers().stream().filter(u -> u.getId() == id).findFirst().orElse(newUser());
	}
	private User newUser() {
		return User.builder().id(0L).name("New User").email("newuser@teste.com").status(UserStatus.ACTIVE.getCodigo()).build();
	}

	private List<User> getUsers() {
		return Stream.of(User.builder().id(1L).name("Daniel").email("teste@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(2L).name("Mafalda").email("teste2@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(3L).name("Michel").email("teste3@teste.com").status(UserStatus.ACTIVE.getCodigo()).build())
				.collect(Collectors.toList());


	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#findById(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	void testFindById() throws Exception {
		User user = getUser(1L);
		when(service.findById(user.getId())).thenReturn(user);

		this.mockMvc
		.perform(get(URL_USERS_FIND_BY_ID,user.getId()).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(responseBody().containsObjectAsResponseEntity(user, User.class))
		.andExpect(status().isOk());
	}
	@Test
	void testNotFindById() throws Exception {
		Long userId = 0L;

		when(service.findById(userId)).thenThrow(new UserNotFoundException(userId));

		this.mockMvc
		.perform(get(URL_USERS_FIND_BY_ID,userId).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException));
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#getUsers()}.
	 * @throws Exception 
	 */
	@Test
	void testGetUsers() throws Exception {
		List<User> users = getUsers();

		when(service.findAll()).thenReturn(users);

		this.mockMvc.perform(get(URL_USERS).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#create(br.com.springboot.app.domain.User)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	void testCreate() throws JsonProcessingException, Exception {
		User user = getUser(0L);

		when(service.save(user)).thenReturn(user);

		mockMvc.perform(post(URL_USERS_CREATE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJson(user)))
		.andDo(print())
		.andExpect(responseBody().containsObjectAsResponseEntity(user, User.class))
		.andExpect(status().isCreated());
	}

	@Test
	void testCreateUserNameRequired() throws JsonProcessingException, Exception {
		User user = getUser(0L);
		user.setName("");

		mockMvc.perform(post(URL_USERS_CREATE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(responseBody().containsError("User name is required."));
	}

	@Test
	void testCreateUserEmailRequired() throws JsonProcessingException, Exception {
		User user = getUser(0L);
		user.setEmail("");

		mockMvc.perform(post(URL_USERS_CREATE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(responseBody().containsError("User email is required."));
	}

	@Test
	void testCreateUserEmailInvalid() throws JsonProcessingException, Exception {
		User user = getUser(0L);
		user.setEmail("askdjadjajdajs");

		mockMvc.perform(post(URL_USERS_CREATE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(responseBody().containsError("User email must be valid."));
	}

	@Test
	void testCreateUserStatusRequired() throws JsonProcessingException, Exception {
		User user = getUser(0L);
		user.setStatus("");

		mockMvc.perform(post(URL_USERS_CREATE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(responseBody().containsError("User status is required."));
	}
	@Test
	void testCreateUserStatusInvalid() throws JsonProcessingException, Exception {
		User user = getUser(0L);
		user.setStatus("User status is required.");

		mockMvc.perform(post(URL_USERS_CREATE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(obterJson(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(responseBody().containsError("User status is a character, 'A' or 'I'."));
	}
	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#update(br.com.springboot.app.domain.User)}.
	 */
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#inactivate(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	void testInactivate() throws Exception {
		User user = getUser(1L);
		user.setStatus(UserStatus.INACTIVE.getCodigo());

		when(service.inactivate(user.getId())).thenReturn(user);

		mockMvc.perform(put(URL_USERS_INACTIVATE,user.getId())
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(responseBody().containsObjectAsResponseEntity(user, User.class))
		.andExpect(status().isOk());
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#active(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	void testActive() throws Exception {
		User user = getUser(1L);
		user.setStatus(UserStatus.ACTIVE.getCodigo());

		when(service.active(user.getId())).thenReturn(user);

		mockMvc.perform(put(URL_USERS_ACTIVATE,user.getId())
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(responseBody().containsObjectAsResponseEntity(user, User.class))
		.andExpect(status().isOk());
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#getEntityModelToResponseEntity(org.springframework.hateoas.EntityModel)}.
	 */
	@Test
	void testGetEntityModelToResponseEntity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#UserController(UserService, UserModelAssembler)}.
	 */
	@Test
	void testUserController() {
		assertThat(controller).isNotNull();
	}

}
