package com.rmiranda.schoolmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.transaction.Transactional;

import com.rmiranda.schoolmanagement.model.entity.Role;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.model.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

        Role role = em.createQuery("from Role r where id = 1", Role.class).getSingleResult();
        List<Role> roles = new ArrayList<Role>();

        roles.add(role);


        newUser.setRoles(roles);

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

    @Test
    public void testGetPaginatedUserList() {

        Pageable page = PageRequest.of(0, 10);

        Page<User> users = userRepository.findAll(page);

        assertTrue(users.getSize() > 0);

    }

    @Test
    public void  testUpdateUser() {
        User user = em.find(User.class, Long.valueOf(1));

        user.setName("Updated name");

        userRepository.save(user);

        User updatedUSer = em.find(User.class, Long.valueOf(1));

        assertTrue(user.getName().equals(updatedUSer.getName()));
    }

}