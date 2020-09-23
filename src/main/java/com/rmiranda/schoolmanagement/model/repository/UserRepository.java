package com.rmiranda.schoolmanagement.model.repository;


import com.rmiranda.schoolmanagement.model.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findUSerByUsername(String username);

    public Page<User> findByDeletedAtIsNull(Pageable pageable);

}