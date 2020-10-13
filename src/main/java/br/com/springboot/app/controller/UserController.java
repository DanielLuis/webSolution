package br.com.springboot.app.controller;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

	private UserService service;

	@Operation(summary = "Find User by ID", responses = {
			@ApiResponse(responseCode = "200", description = "Successful Find Users"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@GetMapping(value = "/findById/{id}")
	public EntityModel<User> findById(@PathVariable Long id) {
		return service.findById(id);
	}


	@Operation(summary = "Get Users", responses = {
			@ApiResponse(responseCode = "200", description = "Successful Find Users"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@GetMapping(value = "/")
	public CollectionModel<EntityModel<User>> getUsers() {
		List<EntityModel<User>> users = service.findAll();
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<User>> create(@RequestBody User user) {
		EntityModel<User> userModel = service.save(user);

		return ResponseEntity //
				.created(linkTo(methodOn(UserController.class).findById(user.getId())).toUri()) //
				.body(userModel);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<User>> update(@RequestBody User user) {
		return service.update(user);
	}

	@DeleteMapping(value = "/inativate/{id}")
	public void inativate(@PathVariable Long id) {
		service.delete(id);
	}

}
