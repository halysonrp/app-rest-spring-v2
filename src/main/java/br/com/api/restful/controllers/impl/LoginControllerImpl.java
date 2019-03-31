package br.com.api.restful.controllers.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.dtos.LoginDto;
import br.com.api.restful.entities.User;
import br.com.api.restful.responses.Response;
import br.com.api.restful.services.impl.LoginServiceImpl;
import br.com.api.restful.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/login")
public final class LoginControllerImpl extends AbstractControllerImpl {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	LoginServiceImpl loginServiceImpl;

	@GetMapping
	public ResponseEntity<Response<User>> login(@Valid @RequestBody LoginDto login, BindingResult result) {
		Response<User> response = new Response<User>();

		if (result.hasErrors()) {
			return isBadRequest(response, result);
		}
		User user = loginServiceImpl.login(login);
		return validLogin(login, user, response);
	}

	public ResponseEntity<Response<User>> validLogin(LoginDto login, User user, Response<User> response) {
		if (user == null) {
			return addResponseMessageError("Usu�rio e/ou senha inv�lidos", response, HttpStatus.BAD_REQUEST);
		} else if (!login.getPassword().equals(user.getPassword())) {
			return addResponseMessageError("Usu�rio e/ou senha inv�lidos", response, HttpStatus.UNAUTHORIZED);
		} else {
			response.setData(user);
			return ResponseEntity.ok(response);
		}
	}
}