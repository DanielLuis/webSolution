package br.com.springboot.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.app.assembler.UserModelAssembler;
import br.com.springboot.app.domain.User;
import br.com.springboot.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "users", description = "User API")
@AllArgsConstructor
public class UserController {

	private final UserService service;
	private final UserModelAssembler assembler;


	@Operation(summary = "Find User by ID", responses = {
			@ApiResponse(responseCode = "200", description = "Successful find one user"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	
	@GetMapping(value = "/findById/{id}")
	public EntityModel<User> findById(@PathVariable Long id) {
		return assembler.toModel(service.findById(id));
	}


	@Operation(summary = "Get Users", responses = {
			@ApiResponse(responseCode = "200", description = "Successful find users"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@GetMapping(value = "/")
	public CollectionModel<EntityModel<User>> getUsers() {
		List<EntityModel<User>> users = service.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).getUsers()).withSelfRel());
	}

	
	@Operation(summary = "Create User ", responses = {
			@ApiResponse(responseCode = "200", description = "Successful create one user"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<User>> create(@Valid @RequestBody  User user) {
		EntityModel<User> userModel = assembler.toModel(service.save(user));

		return getEntityModelToResponseEntity(userModel,HttpStatus.CREATED);
	}

	@Operation(summary = "Update User ", responses = {
			@ApiResponse(responseCode = "200", description = "Successful update one user"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntityModel<User>> update(@Valid @RequestBody  User user) {
//		EntityModel<User> userModel = assembler.toModel(service.update(user));
//		return getEntityModelToResponseEntity(userModel);
		return null;
	}


	@Operation(summary = "Inactivate User ", responses = {
			@ApiResponse(responseCode = "200", description = "Successful inactivate one user"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@PutMapping(value = "/inactivate/{id}")
	public ResponseEntity<EntityModel<User>> inactivate(@PathVariable Long id) {
		EntityModel<User> userModel = assembler.toModel(service.inactivate(id));
		return getEntityModelToResponseEntity(userModel,HttpStatus.OK);
	}

	@Operation(summary = "Active User ", responses = {
			@ApiResponse(responseCode = "200", description = "Successful active one user"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "404", description = "Not found"),
			@ApiResponse(responseCode = "401", description = "Authentication Failure") })
	@PutMapping(value = "/activate/{id}")
	public ResponseEntity<EntityModel<User>> active(@PathVariable Long id) {
		EntityModel<User> userModel = assembler.toModel(service.active(id));
		return getEntityModelToResponseEntity(userModel,HttpStatus.OK);
	}

	public ResponseEntity<EntityModel<User>> getEntityModelToResponseEntity(EntityModel<User> userModel,HttpStatus httpStatus) {
		return ResponseEntity
				.status(httpStatus)
				.location(userModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
				.body(userModel);
	}


}
