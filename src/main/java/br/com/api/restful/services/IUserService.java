package br.com.api.restful.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.api.restful.entities.User;

@Service
public interface IUserService {
	
	User findById(UUID id);
	
	User saveUser(User user);
	
	boolean deleteUser(User user);
	
	List<User> findAll();

}
