package com.msp.givn.service.role;

import com.msp.givn.entity.Role;

import java.util.List;

public interface RoleService {

    Role findById(int id);

    List<Role> findAll();

    Role save(Role role);

    Role findByName(String name);
}
