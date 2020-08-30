package com.rmiranda.schoolmanagement.service;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Role;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleById(long id);
    
}