package br.com.api.restful.services;

import java.util.List;
import java.util.UUID;

import br.com.api.restful.entities.User;

public interface IUserService {

	User findByEmail(String email);
	
	User findById(UUID id);
	
	List<User> findAll();
	
	User save(User entity);
	
	void delete(UUID id);

	void emailValidation(String email);


}
