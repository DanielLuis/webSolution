package br.com.springboot.app.service;


import br.com.springboot.app.assembler.UserModelAssembler;
import br.com.springboot.app.domain.User;
import br.com.springboot.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class UserService 
{
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private UserModelAssembler assembler;
	
	public List<EntityModel<User>> findAll(){
		return repository.findAll().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
	}

	public EntityModel<User> findById(Long id) {
		User user = repository.findById(id).orElse(new User());
		return assembler.toModel(user);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public EntityModel<User> save(User user) {
		return assembler.toModel(repository.save(user));
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public EntityModel<User> update(User user) {
		return assembler.toModel(repository.save(user));
	}

}
