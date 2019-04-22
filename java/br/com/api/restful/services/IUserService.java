package br.com.api.restful.services;

import br.com.api.restful.entities.User;

public interface IUserService extends IGenericService<User> {

	User findByEmail(String email);

}
