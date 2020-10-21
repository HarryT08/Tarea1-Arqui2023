package com.rmiranda.schoolmanagement.service;

import java.util.Optional;

import com.rmiranda.schoolmanagement.model.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public void addUSer(User user);
    
    public Page<User> getAllUSers(Pageable pageable);

    public void updateUser(User user);

    public Optional<User> getUserById(long id);

    public void deleteUserById(long id);

    public void resetPassword(long id, String password);

}