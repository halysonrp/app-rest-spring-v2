package br.com.api.restful.controllers.impl;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.controllers.abstracts.AbstractController;
import br.com.api.restful.dtos.LoginDTO;
import br.com.api.restful.dtos.UserDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.responses.Response;
import br.com.api.restful.services.impl.LoginServiceImpl;

@RestController
@RequestMapping("/api/login")
public final class LoginControllerImpl extends AbstractController<UserDTO, User, LoginServiceImpl> {

	@GetMapping
	public ResponseEntity<Response<UserDTO>> login(@Valid @RequestBody LoginDTO login, BindingResult result) {
		Response<UserDTO> response = new Response<UserDTO>();

		if (result.hasErrors()) {
			return isBadRequest(response, result);
		}
		//TODO convert DTO
		User user = service.login(login);
		return validLogin(login, new UserDTO(), response);
	}

	public ResponseEntity<Response<UserDTO>> validLogin(LoginDTO login, UserDTO user, Response<UserDTO> response) {
		if (user == null) {
			return addResponseMessageError("Usuário e/ou senha inválidos", response, HttpStatus.BAD_REQUEST);
		} else if (!login.getPassword().equals(user.getPassword())) {
			return addResponseMessageError("Usuário e/ou senha inválidos", response, HttpStatus.UNAUTHORIZED);
		} else {
			response.setData(user);
			return ResponseEntity.ok(response);
		}
	}
	
	@Override
	public User convertToEntity(UserDTO dto) {
		return modelMapper.map(dto, User.class);
	}

	@Override
	public UserDTO convertToDTO(User entity) {
		return modelMapper.map(entity, UserDTO.class);
	}
	
	
}
