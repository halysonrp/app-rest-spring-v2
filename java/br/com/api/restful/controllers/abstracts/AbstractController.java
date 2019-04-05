package br.com.api.restful.controllers.abstracts;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import br.com.api.restful.responses.Response;

public abstract class AbstractController<DTO, Entity, Service> {

	@Autowired
	protected Service service;

	@Autowired
	protected ModelMapper modelMapper;

	public ResponseEntity<Response<Entity>> isBadRequest(Response<Entity> response, BindingResult result) {
		result.getAllErrors().forEach(error -> response.getMensagem().add(error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(response);
	}

	public ResponseEntity<Response<Entity>> addResponseMessageError(String message, Response<Entity> response,
			HttpStatus status) {
		List<String> errors = new ArrayList<String>();
		errors.add(message);
		response.setMensagem(errors);
		return ResponseEntity.status(status).body(response);
	}

	public Entity convertToEntity(DTO dto, Class<Entity> entityClass) {
		if(dto != null && entityClass != null) {
			return modelMapper.map(dto, entityClass);
		}
		return null;
	}

	public DTO convertToDTO(Entity entity, Class<DTO> dtoClass) {
		if(entity != null && dtoClass != null) {
			return modelMapper.map(entity, dtoClass);
		}
		return null;
	}
}
