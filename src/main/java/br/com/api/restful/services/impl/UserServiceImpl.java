package br.com.api.restful.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	IUserRepository userRepository;
	
	
	public User findById(UUID id) {
		return new User();	
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public boolean deleteUser(User user) {
		userRepository.delete(user);
		if(findById(user.getId()) != null) {
			return true;
		}
		return false;
	}
	
	public List<User> findAll(){
		return this.userRepository.findAll();
	}

}
