package com.example.demo.models;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "users")
public class User extends BaseEntity{
    private String username;
    private Integer age;
    private Set<Account> accounts;


    public User() {
    }

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
