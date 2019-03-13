package br.com.api.restful.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.api.restful.Autowired;
import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	IUserRepository userRepository;
	
	public User findById() {
		return null;	
	}
	
	public List<User> findAll(){
		return this.userRepository.findAll();
	}

}
