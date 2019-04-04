package br.com.api.restful.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.services.ILoginService;

@Service
public class LoginServiceImpl extends AbstractService<User, IUserRepository> implements ILoginService {

	@Autowired
	IUserRepository userRepository;

	public User findById(UUID id) {
		return new User();
	}

	public User login(LoginDTO login) {
		return userRepository.findByEmail(login.getEmail());
	}

}
