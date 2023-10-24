package br.com.register_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.register_app.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    public User getByEmail(@Param("email") String email);

    public Optional<User> findByUsername(String username);
}
