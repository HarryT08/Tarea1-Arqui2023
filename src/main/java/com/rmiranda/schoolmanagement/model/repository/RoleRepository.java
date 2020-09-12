package com.rmiranda.schoolmanagement.model.repository;

import java.util.List;

import com.rmiranda.schoolmanagement.model.entity.Role;

public interface RoleRepository {

    public List<Role> getAll();

    public Role get(long id);

    public Role findRoleByName(String name);

}