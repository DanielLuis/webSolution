package br.com.springboot.app.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.repository.UserRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class UserService 
{
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public User save(User user) {
		return userRepository.save(user);
	}
	
}
