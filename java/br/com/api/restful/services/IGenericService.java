package br.com.api.restful.services;

import java.util.List;
import java.util.UUID;

import br.com.api.restful.entities.User;

public interface IGenericService<Entity> {
	
	User findById(UUID id);
	
	List<Entity> findAll();
	
	User save(Entity entity);
	
	User update(Entity entity);
	
	void delete(Entity entity);
	
}
