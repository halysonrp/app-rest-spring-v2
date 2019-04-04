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
	
	public ResponseEntity<Response<DTO>> isBadRequest(Response<DTO> response, BindingResult result){
		result.getAllErrors().forEach(error -> response.getMensagem().add(error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(response);
	}
	
	public ResponseEntity<Response<DTO>> addResponseMessageError(String message, Response<DTO> response, HttpStatus status) {
		List<String> errors = new ArrayList<String>();
		errors.add(message);
		response.setMensagem(errors);
		return ResponseEntity.status(status).body(response);
	}
	
	public abstract Entity convertToEntity(DTO dto);

	public abstract DTO convertToDTO(Entity entity);
}
