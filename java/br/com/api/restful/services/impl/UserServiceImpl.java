package br.com.api.restful.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.services.IUserService;

@Service
public class UserServiceImpl extends AbstractService<User, IUserRepository> implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public User findById(UUID id) {
		return repository.findById(id);
	}
	
	@Override
	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	
	@Override
	@Transactional
	public void delete(UUID id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public User save(User user) {
		if(user.getId() != null) {
			User oldUser = repository.findById(user.getId());
			user.setCreated(oldUser.getCreated());
		}
		//user.setToken(jwtToken.obterToken(user.getEmail()));
		return repository.save(user);
	}
}
