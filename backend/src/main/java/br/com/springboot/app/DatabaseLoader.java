package br.com.springboot.app;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.service.UserService;
import br.com.springboot.app.support.UserStatus;


@Component
@Profile("!test")
public class DatabaseLoader {
  @Bean
  CommandLineRunner initUser(UserService userService) { // (1)
    return args -> { 
    	Stream.of(User.builder().id(1L).name("Daniel").email("teste@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(2L).name("Mafalda").email("teste2@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(3L).name("Michel").email("teste3@teste.com").status(UserStatus.ACTIVE.getCodigo()).build())
    	.forEach(user -> { userService.save(user); }
        );
    	
		userService.findAll().forEach(System.out::println);
    };
  }
}
