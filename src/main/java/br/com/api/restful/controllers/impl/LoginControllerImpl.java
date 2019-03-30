package br.com.api.restful.controllers.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.dtos.LoginDto;
import br.com.api.restful.entities.User;
import br.com.api.restful.services.impl.LoginServiceImpl;
import br.com.api.restful.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/login")
public final class LoginControllerImpl {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	LoginServiceImpl loginServiceImpl;

	@GetMapping
	public User login(@RequestBody LoginDto login) {
		 User user = this.loginServiceImpl.login(login);

		return user;
	}
	
	

	/*@PostMapping
	public User insertUser(@RequestBody LoginDto login) {
		return this.userServiceImpl.saveUser(user);
	}
*/
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return this.userServiceImpl.saveUser(user);
	}

	@DeleteMapping(value = "/{id}")
	public User deleteUser(@PathVariable("id") UUID id) {

		return new User();
	}

}
