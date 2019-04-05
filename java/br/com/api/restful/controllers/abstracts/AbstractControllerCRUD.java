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
	public abstract ResponseEntity<Response<Entity>> post(DTO dto,  BindingResult result);

	@PutMapping
	public abstract Entity put(DTO dto);

	@DeleteMapping(value = "/{id}")
	public abstract Entity delete(UUID id);

}
