package br.com.api.restful.controllers.impl;

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

@RestController
@RequestMapping("/api/user")
public final class UserControllerImpl extends AbstractControllerCRUD<UserDTO, User, UserServiceImpl> {

	
	@Override
	public ResponseEntity<Response<UserDTO>> get(@PathVariable("id") UUID id) {
		Response<UserDTO> response = new Response<UserDTO>();
		User user = service.findById(id);
		response.setData(convertToDTO(user));
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<UserDTO>> get() {
		Response<UserDTO> response = new Response<UserDTO>();
		User user = service.findAll().get(0);
		response.setData(convertToDTO(user));
		return ResponseEntity.ok(response);
	}
	
	@Override
	public ResponseEntity<Response<UserDTO>> post(@Valid @RequestBody UserDTO userDto,  BindingResult result) {
		Response<UserDTO> response  = new Response<UserDTO>();
		User user = convertToEntity(userDto);
		if(result.hasErrors()) {
			return isBadRequest(response, result);
		}
		response.setData(convertToDTO(service.save(user)));
		
		return ResponseEntity.ok(response);
	}

	
	@Override
	public UserDTO put(@RequestBody UserDTO user) {
		//return service.saveUser(user);
		return new UserDTO();
	}

	@Override
	public UserDTO delete(@PathVariable("id") UUID id) {

		return new UserDTO();
	}
	
	@Override
	public User convertToEntity(UserDTO dto) {
		if(dto != null) {
			return modelMapper.map(dto, User.class);
		}
		return null;
	}

	@Override
	public UserDTO convertToDTO(User entity) {
		if(entity != null) {
			return modelMapper.map(entity, UserDTO.class);
		}
		return null;
	}
	
	
}



	

	

