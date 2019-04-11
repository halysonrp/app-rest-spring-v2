package br.com.api.restful.controllers.impl.br.com.api.restful.controllers.impl;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restful.controllers.abstracts.AbstractControllerCRUD;
import br.com.api.restful.dtos.UserDTO;
import br.com.api.restful.entities.User;
import br.com.api.restful.responses.Response;
import br.com.api.restful.services.impl.UserServiceImpl;
import br.com.api.restful.utils.PasswordUtils;

@RestController
@RequestMapping("/user")
public final class UserControllerImpl extends AbstractControllerCRUD<UserDTO, User, UserServiceImpl> {

	
	@Override
	public ResponseEntity<Response<User>> get(@PathVariable("id") UUID id) {
		Response<User> response = new Response<User>();
		User user = service.findById(id);
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<User>> get() {
		Response<User> response = new Response<User>();
		User user = service.findAll().get(0);
		response.setData(user);
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<User>> post(@Valid @RequestBody UserDTO userDto,  BindingResult result) {
		Response<User> response  = new Response<User>();
		
		if(result.hasErrors()) {
			return returnResponseStatusHttp(response, result);
		}
		User user = convertToEntity(userDto, User.class);
		user.setPassword(PasswordUtils.generatePasswordBCrypt(user.getPassword()));
		user.getPhones().forEach(phone -> phone.setUser(user));
		
		response.setData(service.save(user));
		return ResponseEntity.ok(response);
	}
	
	

	
	@Override
	public User put(@RequestBody UserDTO user) {
		//return service.saveUser(user);
		return new User();
	}

	@Override
	public User delete(@PathVariable("id") UUID id) {

		return new User();
	}
	
	
}



	

	

