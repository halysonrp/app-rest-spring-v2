package br.com.api.restful.controllers.impl;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.controllers.IUserController;
import br.com.api.restful.controllers.abstracts.AbstractControllerImpl;
import br.com.api.restful.dtos.UserDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.services.IUserService;
import br.com.api.restful.utils.PasswordUtils;

@RestController
@RequestMapping("/user")
public final class UserControllerImpl extends AbstractControllerImpl<UserDTO, User, IUserService> implements IUserController {

	
	
	@Override
	public ResponseEntity<User> get(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@Override
	public ResponseEntity<User> get() {
		return ResponseEntity.ok(service.findAll().get(0));
	}
	
	@Override
	public ResponseEntity<User> post(@Valid @RequestBody UserDTO userDto,  BindingResult result) {
		if(result.hasErrors()) {
			generateResponseError(result);
		}
		User user = convertToEntity(userDto, User.class);
		user.setPassword(PasswordUtils.generatePasswordBCrypt(user.getPassword()));
		user.getPhones().forEach(phone -> phone.setUser(user));
		
		return ResponseEntity.ok(service.save(user));
	}
	
	@Override
	public User put(@RequestBody UserDTO userDTO) {
		return service.save(convertToEntity(userDTO, User.class));
	}

	@Override
	public void delete(@PathVariable("id") UUID id) {
		service.delete(id);
	}
	
	
}



	

	

