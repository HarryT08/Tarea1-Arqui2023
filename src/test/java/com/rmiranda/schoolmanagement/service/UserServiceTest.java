package com.rmiranda.schoolmanagement.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.rmiranda.schoolmanagement.model.entity.User;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void testAddUser() {
        em.createQuery("delete from User u where u.username = :username").setParameter("username", "userservice").executeUpdate();
        User user = new User();
        user.setName("userservice");
        user.setLastName("lastname");
        user.setUsername("userservice");
        user.setPassword("userservice");
        user.setBirthday(new Date());
        userService.addUSer(user);

        User dbUSer = em.createQuery("from User u where u.username = :username", User.class).setParameter("username", "userservice")
                .getSingleResult();

        assertTrue(user.getUsername().equals(dbUSer.getUsername()));
    }

}