package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Role;
import com.rmiranda.schoolmanagement.model.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.getAll();
    }

    @Override
    public Role getRoleById(long id) {
        return roleRepository.get(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
    
}