package br.com.springboot.app.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.springboot.app.repository.UserRepository;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class UserServiceTest {
	
	@Mock
    private UserRepository repository;

    @InjectMocks // auto inject
    private UserService service ;

    @BeforeEach
    void setMockOutput() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
    }

	@Test
	void testFindAll() {
		assertNotNull(service.findAll());
	}

	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testInactivate() {
		fail("Not yet implemented");
	}

	@Test
	void testActive() {
		fail("Not yet implemented");
	}

	@Test
	void testChangeStatus() {
		fail("Not yet implemented");
	}

	@Test
	void testUserService() {
		fail("Not yet implemented");
	}

}
