package br.com.api.restful.controllers.abstracts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.api.restful.entities.AbstractEntity;

public abstract class AbstractControllerListImpl<DTO,  Entity extends AbstractEntity, Service> extends AbstractControllerImpl<DTO,  Entity, Service> {
	
	@GetMapping(value = "/{id}")
	public abstract ResponseEntity<Entity> get(UUID id);
	
	@GetMapping(value = "/")
	public abstract ResponseEntity<Entity> get();
	
}