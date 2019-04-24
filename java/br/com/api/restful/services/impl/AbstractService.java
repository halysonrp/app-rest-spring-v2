package br.com.api.restful.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<Entity, Repository extends JpaRepository<Entity, Long>> {

	@Autowired
	Repository repository;

	public Entity update(Entity entity) {
		return repository.save(entity);
	}

	public void delete(Entity user) {
		repository.delete(user);
	}

	public List<Entity> findAll() {
		return repository.findAll();
	}

}
