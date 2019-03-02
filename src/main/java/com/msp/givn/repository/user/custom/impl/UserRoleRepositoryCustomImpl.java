package com.msp.givn.repository.user.custom.impl;

import com.msp.givn.repository.user.custom.UserRoleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserRoleRepositoryCustomImpl implements UserRoleRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateRoleForUser(int userId, int roleId) {
        Query query = entityManager.createNativeQuery("UPDATE user_role AS U SET U.role_id = ? WHERE U.user_id = ?");
        query.setParameter(1, roleId);
        query.setParameter(2, userId);
        query.executeUpdate();
    }
}
