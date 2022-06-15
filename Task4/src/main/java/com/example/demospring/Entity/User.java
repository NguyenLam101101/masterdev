package com.example.demospring.Entity;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", length = 45, nullable = true)
    private String email;

    @Column(name = "password", length = 45, nullable = true)
    public String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}