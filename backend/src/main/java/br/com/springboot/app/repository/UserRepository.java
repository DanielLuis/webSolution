package br.com.springboot.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.springboot.app.domain.User;


public interface UserRepository extends CrudRepository<User, Long> {
	public List<User> findAll();
	public List<User> findByName(String name);
	
	


}
