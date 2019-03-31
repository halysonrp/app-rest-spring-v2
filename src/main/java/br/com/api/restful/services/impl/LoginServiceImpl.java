package br.com.api.restful.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.restful.dtos.LoginDto;
import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;

@Service
public class LoginServiceImpl {

	@Autowired
	IUserRepository userRepository;

	public User findById(UUID id) {
		return new User();
	}

	public User login(LoginDto login) {
		return userRepository.findByEmail(login.getEmail());
	}

}
