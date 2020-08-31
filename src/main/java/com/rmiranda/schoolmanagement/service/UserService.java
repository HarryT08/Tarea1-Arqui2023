package com.rmiranda.schoolmanagement.service;

import com.rmiranda.schoolmanagement.model.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public void addUSer(User user);
    
    public Page<User> getAllUSers(Pageable pageable);

}