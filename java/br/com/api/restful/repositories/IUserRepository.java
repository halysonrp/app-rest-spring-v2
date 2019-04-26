package br.com.api.restful.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.restful.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	User findById(UUID id);
	
	User findByEmail(String email);

	void deleteById(UUID id);
	

}
