package br.com.springboot.app.controller;

import br.com.springboot.app.assembler.UserModelAssembler;
import br.com.springboot.app.domain.User;
import br.com.springboot.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

	private final UserService service;
	private final UserModelAssembler assembler;


	@Operation(summary = "Find User by ID", responses = {
			@ApiResponse(responseCode = "200", description = "Successful Find Users"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@GetMapping(value = "/findById/{id}")
	public EntityModel<User> findById(@PathVariable Long id) {
		return assembler.toModel(service.findById(id));
	}


	@Operation(summary = "Get Users", responses = {
			@ApiResponse(responseCode = "200", description = "Successful Find Users"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@GetMapping(value = "/")
	public CollectionModel<EntityModel<User>> getUsers() {
		List<EntityModel<User>> users = service.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<User>> create(@RequestBody @Valid User user) {
		EntityModel<User> userModel = assembler.toModel(service.save(user));

		return getEntityModelToResponseEntity(userModel);
	}

	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<User>> update(@RequestBody @Valid User user) {
		EntityModel<User> userModel = assembler.toModel(service.update(user));
		return getEntityModelToResponseEntity(userModel);
	}


	@DeleteMapping(value = "/inactivate/{id}")
	public void inactivate(@PathVariable Long id) {
		service.inactivate(id);
	}

	public ResponseEntity<EntityModel<User>> getEntityModelToResponseEntity(EntityModel<User> userModel) {
		return ResponseEntity //
				.created(userModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(userModel);
	}


}
