package com.rmiranda.schoolmanagement.service;

import java.util.Optional;


import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void addUSer(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Page<User> getAllUSers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void updateUser(User user) {
        User dbUser = userRepository.getOne(user.getId());

        dbUser.setName(user.getName());
        dbUser.setLastName(user.getLastName());
        dbUser.setBirthday(user.getBirthday());
        dbUser.setEmail(user.getEmail());
        dbUser.setRoles(user.getRoles());

        userRepository.save(dbUser);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

}