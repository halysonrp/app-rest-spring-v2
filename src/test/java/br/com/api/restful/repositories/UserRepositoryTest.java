package br.com.api.restful.repositories;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.restful.entities.Phone;
import br.com.api.restful.entities.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private IUserRepository userRepository;
	
	private static final String EMAIL = "halyson.ribeiro@gmail.com";
	private UUID idUser;
	
	@Before
	public void setUp() throws Exception {
		User user = userRepository.save(createUser());
		idUser = user.getId();
	}
	
	
	@After
	public final void tearDown() {
		this.userRepository.deleteAll();
	}
	
	@Test
	public void testFindByEmail() {
		User user = userRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, user.getEmail());
	}
	
	@Test
	public void testFindById() {
		User user = userRepository.findById(idUser);
		assertEquals(idUser, user.getId());
	}
	
	public User createUser() {
		User user = new User();
		user.setEmail(EMAIL);
		user.setName("Halyson");
		user.setPassword("123");
		user.setToken("123456");
		
		Phone phone = new Phone();
		phone.setDdd("081");
		phone.setNumber("998322122");
		phone.setUser(user);
		return user;
	}

}
