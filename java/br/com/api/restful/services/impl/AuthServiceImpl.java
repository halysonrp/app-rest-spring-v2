package br.com.api.restful.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.restful.entities.User;
import br.com.api.restful.services.IAuthService;
import br.com.api.restful.services.IUserService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	IUserService userService;
	
	@Override
	public User findByEmail(String email) {
		return userService.findByEmail(email);
	}


}
