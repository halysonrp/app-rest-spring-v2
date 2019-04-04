package br.com.api.restful.controllers.abstracts;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.restful.responses.Response;

public abstract class AbstractControllerCRUD<DTO, Entity, Service> extends AbstractControllerList<DTO, Entity, Service> {
	
	@PostMapping
	public abstract ResponseEntity<Response<DTO>> post(DTO dto,  BindingResult result);

	@PutMapping
	public abstract DTO put( DTO dto);

	@DeleteMapping(value = "/{id}")
	public abstract DTO delete(UUID id);

}
