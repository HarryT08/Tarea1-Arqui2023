package com.rmiranda.schoolmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.transaction.Transactional;

import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.model.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testAddUSer() {

        em.createQuery("delete from User u where u.username = :username").setParameter("username", "rmiranda")
                .executeUpdate();

        em.setFlushMode(FlushModeType.COMMIT);

        User newUser = new User();
        newUser.setName("Ronald");
        newUser.setLastName("Miranda");
        newUser.setUsername("rmiranda");
        newUser.setPassword("password");
        newUser.setBirthday(new Date());

        userRepository.save(newUser);

        User findUSer = em.createQuery("from User u where u.username = :username", User.class)
                .setParameter("username", "rmiranda").getSingleResult();


        assertTrue(newUser.getUsername().equals(findUSer.getUsername()));
    }

    @Test
    public void testFindUserByUsername() {
        User user = userRepository.findUSerByUsername("system");

        assertTrue(user.getUsername().equals("system"));
    }
}