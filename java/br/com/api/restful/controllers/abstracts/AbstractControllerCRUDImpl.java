package br.com.api.restful.controllers.abstracts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.api.restful.entities.AbstractEntity;

public abstract class AbstractControllerCRUDImpl<DTO,  Entity extends AbstractEntity, Service> extends AbstractControllerListImpl<DTO, Entity, Service> {
	
	@PostMapping
	public abstract ResponseEntity<Entity> post(DTO dto,  BindingResult result);

	@PutMapping
	public abstract Entity put(DTO dto);

	@DeleteMapping(value = "/{id}")
	public abstract Entity delete(UUID id);

}
