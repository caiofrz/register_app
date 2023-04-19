package br.com.register_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.register_app.model.User;
import br.com.register_app.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return (List<User>) repository.findAll();
    }


    @PostMapping(value = "/users")
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }


}
