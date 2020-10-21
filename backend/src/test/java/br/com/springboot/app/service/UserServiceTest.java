package br.com.springboot.app.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.springboot.app.domain.User;
import br.com.springboot.app.exceptions.UserNotFoundException;
import br.com.springboot.app.repository.UserRepository;
import br.com.springboot.app.support.UserStatus;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
    private UserRepository repository;

    @InjectMocks // auto inject
    private UserService service ;

   
	private User getUser(Long id) {
		return getUsers().stream().filter(u -> u.getId() == id).findFirst().orElse(newUser());
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
	void testFindAll() {
		List<User> users = getUsers();
		
        when(repository.findAll()).thenReturn(users);

        List<User> actualUsers = service.findAll();

        assertSame(actualUsers,users);
        assertNotNull(actualUsers);
        assertEquals(actualUsers.size(), users.size());
        verify(repository, times(1)).findAll();
	}

	@Test
	void testFindById() {
		User user = getUser(1L);

		when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        
        User actualUser = service.findById(user.getId());

        assertSame(actualUser,user);
        assertNotNull(actualUser);
	}
	
	@Test
	void testNotFindById() throws UserNotFoundException {
		Long userId = 0L;

		when(repository.findById(userId)).thenThrow(new UserNotFoundException(userId));

        assertThrows(UserNotFoundException.class,() -> service.findById(userId));
	}

	@Test
	void testSave() {
		User user = getUser(0L);

		when(repository.save(user)).thenReturn(user);
		
		User userSaved = service.save(user);
		
		assertNotNull(userSaved);
		
		verify(repository,times(1)).save(userSaved);
	}

	@Test
	void testInactivate() {
		User user = getUser(1L);
		user.setStatus(UserStatus.INACTIVE.getCodigo());
		
		when(repository.findById(user.getId())).thenReturn(Optional.of(user));
		when(repository.save(user)).thenReturn(user);
		
		User userSaved = service.inactivate(user.getId());
		
		assertNotNull(userSaved);
		assertEquals(userSaved.getStatus(),UserStatus.INACTIVE.getCodigo());
		
		verify(repository,times(1)).save(userSaved);
	}

	@Test
	void testActive() {
		User user = getUser(1L);
		user.setStatus(UserStatus.ACTIVE.getCodigo());

		when(repository.findById(user.getId())).thenReturn(Optional.of(user));
		when(repository.save(user)).thenReturn(user);
		
		User userSaved = service.active(user.getId());
		
		assertNotNull(userSaved);
		assertEquals(userSaved.getStatus(),UserStatus.ACTIVE.getCodigo());
		
		verify(repository,times(1)).save(userSaved);
	}

	@Test
	void testChangeStatusActive() {
		User userActive = getUser(1L);
		userActive.setStatus(UserStatus.ACTIVE.getCodigo());
		
		when(repository.findById(userActive.getId())).thenReturn(Optional.of(userActive));
		when(repository.save(userActive)).thenReturn(userActive);
		
		User userActiveSaved = service.changeStatus(userActive.getId(), UserStatus.ACTIVE);
		
		assertNotNull(userActiveSaved);
		assertEquals(userActiveSaved.getStatus(),UserStatus.ACTIVE.getCodigo());

		verify(repository,times(1)).findById(userActiveSaved.getId());
		verify(repository,times(1)).save(userActiveSaved);
	}
	@Test
	void testChangeStatusInactive() {
		User userInactive = getUser(2L);
		userInactive.setStatus(UserStatus.INACTIVE.getCodigo());
		
		when(repository.findById(userInactive.getId())).thenReturn(Optional.of(userInactive));
		when(repository.save(userInactive)).thenReturn(userInactive);
		
		User userInactiveSaved = service.changeStatus(userInactive.getId(), UserStatus.INACTIVE);
		
		assertNotNull(userInactive);
		assertEquals(userInactiveSaved.getStatus(),UserStatus.INACTIVE.getCodigo());
		
		verify(repository,times(1)).findById(userInactiveSaved.getId());
		verify(repository,times(1)).save(userInactiveSaved);
	}

	@Test
	void testUserService() {
		assertThat(service).isNotNull();
	}

}
