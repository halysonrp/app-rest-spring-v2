package br.com.api.restful.controllers.abstracts;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.validation.BindingResult;

import br.com.api.restful.entities.AbstractEntity;
import br.com.api.restful.securitys.exceptions.BusinessException;

public abstract class AbstractControllerImpl<DTO, Entity extends AbstractEntity, Service> {

	@Autowired
	protected Service service;

	@Autowired
	protected ModelMapper modelMapper;

	public void generateResponseError(BindingResult result) {
		BusinessException businessException = new BusinessException();
		result.getAllErrors().forEach(error -> businessException.addMessages(error.getDefaultMessage()));
		throw  businessException;
	}

	public ResponseEntity<Entity> addResponseMessageError(String message, Entity entity,
			HttpStatus status) {
		List<String> errors = new ArrayList<String>();
		errors.add(message);
		entity.setMensagem(errors);
		return ResponseEntity.status(status).body(entity);
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
