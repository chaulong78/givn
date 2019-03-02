package com.msp.givn.service.user.impl;

import com.msp.givn.entity.Role;
import com.msp.givn.entity.UserRole;
import com.msp.givn.repository.role.RoleRepository;
import com.msp.givn.repository.user.UserRoleRepository;
import com.msp.givn.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    @Transactional
    public void updateRoleForUser(int userId, int roleId) {
        userRoleRepository.updateRoleForUser(userId, roleId);
    }

    @Override
    public UserRole createRoleForUser(int userId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        Role role = roleRepository.findByName("ROLE_USER");
        userRole.setRoleId(role.getId());

        return save(userRole);
    }
}
