package br.com.api.restful.controllers.impl;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public final class UserControllerImpl {
	
	@GetMapping(value = "/{id}")
	public String findUserById(@PathVariable("id") UUID id) {
		return "User autentication:" + id;
	}

}
