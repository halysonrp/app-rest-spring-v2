package br.com.api.restful.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.securitys.exceptions.BusinessException;
import br.com.api.restful.securitys.utils.JwtTokenUtil;
import br.com.api.restful.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	JwtTokenUtil jwtToken;
	
	@Override
	public User findById(UUID id) {
		return userRepository.findById(id);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User save(User user) {
		if (user.isNew()) {
			emailValidation(user.getEmail());
		} else {
			User oldUser = userRepository.findById(user.getId());
			user.setCreated(oldUser.getCreated());
		}
		user.setToken(jwtToken.obterToken(user.getEmail()));
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void delete(UUID id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public void emailValidation(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			throw new BusinessException("E-mail já existente");
		}

	}
}
