package com.example.demospring.Controller;

import com.example.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demospring.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("select/{id}")
    public User SelectUser(@PathVariable Long id){
        return service.SelectUser(id);
    }

    @PostMapping("insert")
    public User InsertUser(@RequestBody User user){
        return service.InsertUser(user);
    }

    @DeleteMapping("delete/{id}")
    public String DeleteUser(@PathVariable Long id){
        service.DeleteUser(id);
        return "User {0} is removes".formatted(id);
    }
    @PutMapping("update")
    public User UpdateUser(@RequestBody User user){
        return service.UpdateUser(user);
    }

}