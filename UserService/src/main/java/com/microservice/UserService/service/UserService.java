package com.microservice.UserService.service;

import java.util.List;

import com.microservice.UserService.entity.User;

public interface UserService {

    public User savUser(User user);

    public User getUser(String id);

    public List<User>getAllUsers();
    
}
