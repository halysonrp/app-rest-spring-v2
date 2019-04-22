package br.com.api.restful.services;

import br.com.api.restful.entities.User;

public interface IAuthService {

	User findByEmail(String email);
}
