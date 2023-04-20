package br.com.register_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.register_app.Exception.NotFoundException;
import br.com.register_app.model.User;
import br.com.register_app.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getUsers() {
        return (List<User>)this.repository.findAll();
    }

    public User getUser(Integer id) throws NotFoundException{
        return this.findUser(id);
    }

    public User createUser(User user) {
        this.encondigPassowrd(user);
        return this.repository.save(user);
    }

    public User updateUser(Integer id, User user) throws NotFoundException{
        this.findUser(id);
        user.setId(id);
        this.encondigPassowrd(user);
        return this.repository.save(user);

    }
    public void deleteUser(Integer id) throws NotFoundException{
        this.findUser(id);
        this.repository.deleteById(id); 
    }

    private User findUser(Integer id) throws NotFoundException{
        var user = this.repository.findById(id).orElseThrow(NotFoundException::new);
        return user;
    }

    private void encondigPassowrd(User user){
        String encoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
    }

}
