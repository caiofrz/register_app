package br.com.register_app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.register_app.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    
}
