package br.com.api.restful.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;

public interface IAuthController {

	@PostMapping
	ResponseEntity<User> authenticate(LoginDTO authDTO, BindingResult result);

}
