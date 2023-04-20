package br.com.register_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.register_app.Exception.NotFoundException;
import br.com.register_app.model.User;
import br.com.register_app.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return (List<User>) repository.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable Integer id) throws Exception{
        return this.findUser(id);
    }
    
    @PostMapping(value = "/users")
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping(value = "/user/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) throws NotFoundException{
        user.setId(id);
        return repository.save(user);
    }
    
    @DeleteMapping(value = "/user/{id}")
    public User deleteUser(@PathVariable Integer id) throws NotFoundException{
        var user = findUser(id);
        this.repository.deleteById(id);
        return user;
    }
    
    public User findUser(Integer id) throws NotFoundException{
        var user = this.repository.findById(id).orElseThrow(NotFoundException::new);
        return user;
    }

}
