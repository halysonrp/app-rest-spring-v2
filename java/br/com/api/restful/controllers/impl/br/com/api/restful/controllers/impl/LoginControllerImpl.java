package br.com.api.restful.controllers.impl.br.com.api.restful.controllers.impl;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.controllers.abstracts.AbstractControllerImpl;
import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.services.IAuthService;
import br.com.api.restful.services.impl.AuthServiceImpl;
import br.com.api.restful.utils.PasswordUtils;

@RestController
@RequestMapping("/logins")
public final class LoginControllerImpl extends AbstractControllerImpl<LoginDTO, User, IAuthService> {

	@GetMapping
	public ResponseEntity<User> login(@Valid @RequestBody LoginDTO loginDto, BindingResult result) {
		if (result.hasErrors()) {
			generateResponseError(result);
		}
		User user = service.findByEmail(loginDto.getEmail());
		return validLogin(loginDto, user);
	}

	public ResponseEntity<User> validLogin(LoginDTO login, User user) {
		if (user == null) {
			return addResponseMessageError("Usuário e/ou senha inválidos", user, HttpStatus.BAD_REQUEST);
		} else if (!PasswordUtils.validPassword(login.getPassword(), user.getPassword())){
			return addResponseMessageError("Usuário e/ou senha inválidos", user, HttpStatus.UNAUTHORIZED);
		} else {
			return ResponseEntity.ok(user);
		}
		
	}
	
}
