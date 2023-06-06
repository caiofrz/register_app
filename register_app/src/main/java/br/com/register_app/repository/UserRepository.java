package br.com.register_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.register_app.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    public User getByEmail(@Param("email") String email);
}
