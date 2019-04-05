package br.com.api.restful.controllers.abstracts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.api.restful.responses.Response;

public abstract class AbstractControllerList<DTO, Entity, Service> extends AbstractController<DTO, Entity, Service> {
	
	@GetMapping(value = "/{id}")
	public abstract ResponseEntity<Response<Entity>> get(UUID id);
	
	@GetMapping(value = "/")
	public abstract ResponseEntity<Response<Entity>> get();
	
}
