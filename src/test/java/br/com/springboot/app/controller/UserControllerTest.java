/**
 * 
 */
package br.com.springboot.app.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
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

	private static String URL_USERS = "/users";


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		List<User> users = Stream.of(User.builder().id(1L).name("Daniel").email("teste@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(2L).name("Mafalda").email("teste2@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(3L).name("Michel").email("teste3@teste.com").status(UserStatus.ACTIVE.getCodigo()).build())
				.collect(Collectors.toList());


		users.stream().forEach(u -> when(service.findById(u.getId())).thenReturn(u));

		when(service.findAll()).thenReturn(users);
	}

	public String  obterJson(User user) {
		String json = null;

		try {
			json = objectMapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


		return json;
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#findById(java.lang.Long)}.
	 * @throws Exception 
	 */
	@Test
	void testFindById() throws Exception {
		this.mockMvc
		.perform(get(URL_USERS+"/findById/{id}",1L).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	@Test
	void testNotFindById() throws Exception {
		this.mockMvc
		.perform(get(URL_USERS+"/findById/{id}",6L).contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException));
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#getUsers()}.
	 * @throws Exception 
	 */
	@Test
	void testGetUsers() throws Exception {
		this.mockMvc.perform(get(URL_USERS).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#create(br.com.springboot.app.domain.User)}.
	 */
	@Test
	void testCreate() {
		fail("Not yet implemented");
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
	 */
	@Test
	void testInactivate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link br.com.springboot.app.controller.UserController#active(java.lang.Long)}.
	 */
	@Test
	void testActive() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

}
