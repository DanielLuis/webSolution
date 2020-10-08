package br.com.springboot.app;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.service.UserService;


@Component
public class UserCommandLineRunner implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		Stream.of(new User("Daniel","teste@teste.com"),
				new User("Mafalda","teste2@teste2.com"),
				new User("Michel","teste3@teste3.com")).forEach(user -> userService.save(user)
        );
		userService.findAll().forEach(System.out::println);

	}

}
