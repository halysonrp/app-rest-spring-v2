package br.com.api.restful.controllers.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.Autowired;
import br.com.api.restful.entities.User;
import br.com.api.restful.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public final class UserControllerImpl {
	
	@Autowired
	UserServiceImpl userServiceImpl;

	@GetMapping(value = "/{id}")
	public String findUserById(@PathVariable("id") UUID id) {
		List<User> users = this.userServiceImpl.findAll();
		return "User autentication qtd Entity:" + users.size();
	}

}
