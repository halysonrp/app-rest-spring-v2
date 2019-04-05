package br.com.api.restful.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.api.restful.entities.User;
import br.com.api.restful.repositories.IUserRepository;
import br.com.api.restful.services.IUserService;

@Service
public class UserServiceImpl extends AbstractService<User, IUserRepository> implements IUserService {
	

	public User findById(UUID id) {
		return repository.findById(id);
	}
	
	public boolean deleteUser(User user) {
		repository.delete(user);
		if(findById(user.getId()) != null) {
			return true;
		}
		return false;
	}


	

	

}
