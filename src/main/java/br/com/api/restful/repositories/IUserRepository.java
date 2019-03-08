package br.com.api.restful.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.restful.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
