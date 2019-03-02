package com.msp.givn.service.user;

import com.msp.givn.entity.UserRole;

public interface UserRoleService {

    UserRole save(UserRole userRole);

    void updateRoleForUser(int userId, int roleId);

    UserRole createRoleForUser(int userId);
}
