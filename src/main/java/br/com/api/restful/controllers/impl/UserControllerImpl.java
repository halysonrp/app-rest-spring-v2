package br.com.api.restful.controllers.impl;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.entities.User;
import br.com.api.restful.responses.Response;
import br.com.api.restful.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public final class UserControllerImpl extends AbstractControllerImpl {

	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<User>> findUserById(@PathVariable("id") UUID id) {
		Response<User> response = new Response<User>();
		response.setData(userServiceImpl.findById(id));
		return ResponseEntity.ok(response);
	}
	@GetMapping(value = "/")
	public ResponseEntity<Response<User>> findAllUsers() {
		Response<User> response = new Response<User>();
		response.setData(userServiceImpl.findAll().get(0));
		return ResponseEntity.ok(response);
	}
	

	@PostMapping
	public ResponseEntity<Response<User>> insertUser(@Valid @RequestBody User user,  BindingResult result) {
		Response<User> response  = new Response<User>();
		if(result.hasErrors()) {
			return isBadRequest(response, result);
		}
		response.setData(userServiceImpl.saveUser(user));
		
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		return this.userServiceImpl.saveUser(user);
	}

	@DeleteMapping(value = "/{id}")
	public User deleteUser(@PathVariable("id") UUID id) {

		return new User();
	}

	
}
