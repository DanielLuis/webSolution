package br.com.springboot.app.service;


import br.com.springboot.app.domain.User;
import br.com.springboot.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
@AllArgsConstructor
public class UserService 
{
	private final UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}

	public User findById(Long id) {
		return repository.findById(id).orElse(new User());
	}
//	@Transactional(propagation=Propagation.REQUIRED)
//	public void delete(Long id) {
//		repository.deleteById(id);
//	}

	@Transactional(propagation=Propagation.REQUIRED)
	public User save(User user) {
		return repository.save(user);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public User update(User user) {
		return repository.save(user) ;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void inactivate(Long id) {
		User user = repository.findById(id).orElse(new User());
		repository.deleteById(user.getId());
	}

}
