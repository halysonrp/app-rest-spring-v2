package br.com.api.restful.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.services.IUserService;

@Service
public class UserServiceImpl extends AbstractService<User, IUserRepository> implements IUserService {
	
	//@Autowired
	//private JwtTokenUtil jwtToken;
	
	@Override
	public User findById(UUID id) {
		return repository.findById(id);
	}
	
	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	
	
	public User save(User user) {
		//user.setToken(jwtToken.obterToken(user.getEmail()));
		return repository.save(user);
	}
}
