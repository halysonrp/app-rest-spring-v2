package br.com.api.restful.services;

import br.com.api.restful.entities.User;


public interface IUserService {
	
	User saveUser(User user);
	
	boolean deleteUser(User user);
	

}
