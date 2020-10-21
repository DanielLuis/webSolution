package br.com.springboot.app.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.support.UserStatus;


//@RunWith(SpringRunner.class)
@DataJpaTest
//@Profile("test")
public class UserRepositoryTest {


	@Autowired private UserRepository repository;

	
	private User getUser(Long id) {
		return getUsers().stream().filter(u -> u.getId() == id).findAny().orElse(newUser());
	}
	private User newUser() {
		return User
				.builder()
				.id(0L)
				.name("New User")
				.email("newuser@teste.com")
				.status(UserStatus.ACTIVE.getCodigo()).build();
	}

	private List<User> getUsers() {
		return Stream.of(User.builder().id(1L).name("Daniel").email("teste@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(2L).name("Mafalda").email("teste2@teste.com").status(UserStatus.ACTIVE.getCodigo()).build(),
				User.builder().id(3L).name("Michel").email("teste3@teste.com").status(UserStatus.ACTIVE.getCodigo()).build())
				.collect(Collectors.toList());
	}
	@Test
	public void testFindAll() {
		getUsers().stream().forEach(u -> repository.save(u));
		
		List<User> users = repository.findAll();

		assertThat(users).isNotEmpty();
		assertThat(users).hasSize(3).contains(getUser(1L), getUser(2L), getUser(3L));
	}

	@Test
	public void testNotFindAll() {
		List<User> users = repository.findAll();
		assertThat(users).isEmpty();
	}

	@Test
	public void testSave() {
		User user = getUser(0L);

		User userSaved = repository.save(user);

		assertNotNull(userSaved);
		assertThat(userSaved.getId()).isNotEqualTo(user.getId());
	}

	@Test
	public void testFindById() {
		getUsers().stream().forEach(u -> repository.save(u));

		User user = getUser(2L);

		User userFound = repository.findById(user.getId()).get();

		assertThat(userFound).isEqualTo(user);
	}

	@Test
	public void testFindByName() {
		getUsers().stream().forEach(u -> repository.save(u));

		User user = getUser(2L);
		List<User> usersFound = repository.findByName(user.getName());

		assertThat(usersFound).allMatch(u -> u.getName().equals(user.getName()));
	}

	@Test
	public void testUpdate() {
		getUsers().stream().forEach(u -> repository.save(u));

		User updateUser = getUser(3L);
		updateUser.setEmail("emilass@difernte.com");
		updateUser.setName("Kako");

		User user = repository.findById(updateUser.getId()).get();

		user.setEmail(updateUser.getEmail());
		user.setName(updateUser.getName());

		user = repository.save(user);

		assertNotNull(user);
		assertSame(user,updateUser);
	}

	@Test
	public void testDeleteById() {
		getUsers().stream().forEach(u -> repository.save(u));

		User user = getUser(1L);

		repository.deleteById(user.getId());

		List<User> users = repository.findAll();

		assertThat(users).hasSize(2).contains(getUser(2L), getUser(3L));
	}

	@Test
	public void testDeleteAll() {
		getUsers().stream().forEach(u -> repository.save(u));

		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}

	@Test
	public void testRepository() {
		assertThat(repository).isNotNull();
	}

}
