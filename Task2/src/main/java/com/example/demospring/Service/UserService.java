package com.example.demospring.Service;

import com.example.demospring.Entity.User;
import com.example.demospring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User SelectUser(Long id){
        return repository.findById(id).orElse(null);
    }

    public User InsertUser(User user){
        return repository.save(user);
    }

    public String DeleteUser(Long id){
        repository.deleteById(id);
        return "User {0} is removes".formatted(id);
    }

    public User UpdateUser(User user){
        User curUser = repository.findById(user.getId()).orElse(null);
        curUser.setEmail(user.getEmail());
        curUser.setPassword(user.getPassword());
        return repository.save(curUser);
    }

}