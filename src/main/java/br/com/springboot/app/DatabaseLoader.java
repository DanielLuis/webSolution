package br.com.springboot.app;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.service.UserService;


@Component
public class DatabaseLoader {
  @Bean
  CommandLineRunner initUser(UserService userService) { // (1)
    return args -> { 
    	Stream.of(new User(1L,"Daniel","teste@teste.com"),
				new User(2L,"Mafalda","teste2@teste2.com"),
				new User(3L,"Michel","teste3@teste3.com")).forEach(user -> { userService.save(user); }
        );
    	
		userService.findAll().forEach(System.out::println);
    };
  }
}
