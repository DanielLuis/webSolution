package br.com.springboot.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "users")
public class UserController {
	@Autowired
	private UserService userService;
	

	@RequestMapping(value = "/" , method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUser() {
		return userService.findAll();
	}
	
	@RequestMapping(value = "/create" , method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public User create(@RequestBody User channel) {
		return userService.save(channel);
	}
	

}
