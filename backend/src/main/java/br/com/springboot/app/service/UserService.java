package br.com.springboot.app.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.exceptions.UserNotFoundException;
import br.com.springboot.app.repository.UserRepository;
import br.com.springboot.app.support.UserStatus;
import lombok.AllArgsConstructor;

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
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
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
	public User inactivate(Long id) {
		return changeStatus(id, UserStatus.INACTIVE);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public User active(Long id) {
		return changeStatus(id, UserStatus.ACTIVE);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public User changeStatus(Long id,UserStatus userStatus) {
		User user = findById(id);
		user.setStatus(userStatus.getCodigo());
		return save(user);
	}

}
