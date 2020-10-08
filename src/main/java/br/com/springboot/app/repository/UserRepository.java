package br.com.springboot.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.app.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public List<User> findAll();
	
	


}
