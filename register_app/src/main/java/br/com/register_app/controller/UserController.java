package br.com.register_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.register_app.Exception.NotFoundException;
import br.com.register_app.model.User;
import br.com.register_app.service.UserService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*") //Liberar entradas da mesma máquina
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.service.getUsers());
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK)
                             .body(this.service.getUser(id));
    }
    
    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.service.createUser(user));
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(this.service.updateUser(id, user));
    }
    
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) throws NotFoundException{
        this.service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
