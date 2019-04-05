package br.com.api.restful.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;


public interface ILoginService {

	User findById(UUID id);

	User login(LoginDTO login);
}
