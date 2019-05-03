package br.com.api.restful.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.dtos.UserDTO;
import br.com.api.restful.entities.User;


public interface IUserController {

	@GetMapping
	ResponseEntity<User> get(UUID id);

	@PostMapping
	ResponseEntity<User> post(@Valid UserDTO userDto, BindingResult result);

	@PutMapping
	User put(UserDTO userDTO);

	@DeleteMapping
	void delete(UUID id);

	
	
	
}
