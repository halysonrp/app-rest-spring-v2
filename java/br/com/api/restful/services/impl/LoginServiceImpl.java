package br.com.api.restful.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.services.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService, UserDetailsService {

	@Autowired
	IUserRepository userRepository;

	public User findById(UUID id) {
		return new User();
	}

	public User login(LoginDTO login) {
		return userRepository.findByEmail(login.getEmail());
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}

}
