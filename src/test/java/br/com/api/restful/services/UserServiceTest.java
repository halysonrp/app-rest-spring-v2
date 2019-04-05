package br.com.api.restful.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.services.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	//@MockBean
	//private IUserRepository userRepository;
	
	@Autowired
	private UserServiceImpl userService;
	
	private UUID idUser = UUID.randomUUID();

	
	@Before
	public void setUp() throws Exception {
		//BDDMockito.given(this.userRepository.findById(idUser)).willReturn(new User());
		//BDDMockito.given(this.userRepository.save(Mockito.any(User.class))).willReturn(new User());
	}
	
	@Test
	public void testFindById() {
		//User user = userService.findById(idUser);
		//assertNotNull(user);
	}

}
