package com.rmiranda.schoolmanagement.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.rmiranda.schoolmanagement.model.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    public void testGetPaginatedUSers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> users = userService.getAllUSers(pageable);

        assertTrue(users.getSize() > 0);
    }

}