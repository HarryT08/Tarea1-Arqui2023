package com.rmiranda.schoolmanagement.model.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.rmiranda.schoolmanagement.model.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Role> getAll() {
        return em.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role get(long id) {
        return em.find(Role.class, id);
    }
    
}