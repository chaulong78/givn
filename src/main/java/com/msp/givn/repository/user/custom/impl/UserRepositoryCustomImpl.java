package com.msp.givn.repository.user.custom.impl;

import com.msp.givn.repository.user.custom.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getPasswordById(int id) {
        Query query = entityManager.createNativeQuery("SELECT U.password FROM user AS U WHERE U.id = ?");
        query.setParameter(1, id);

        return (String) query.getSingleResult();
    }

    @Override
    public boolean updatePassword(int id, String password) {
        StringBuilder sql = new StringBuilder("UPDATE user AS U SET U.password = ");
        sql.append("'");
        sql.append(password);
        sql.append("'");
        sql.append(" WHERE U.id = " + id);
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.executeUpdate() > 0;
    }

    @Override
    public void updateEnabled(int id, boolean enabled) {
        Query query = entityManager.createNativeQuery("UPDATE user SET enabled = ? WHERE id = ?");
        query.setParameter(1, enabled);
        query.setParameter(2, id);

        query.executeUpdate();
    }

    @Override
    public void updateAvatar(int id, String avatar) {
        StringBuilder sql = new StringBuilder("UPDATE user AS U SET U.avatar = ");
        sql.append("'");
        sql.append(avatar);
        sql.append("'");
        sql.append(" WHERE U.id = " + id);
        Query query = entityManager.createNativeQuery(sql.toString());
        query.executeUpdate();
    }

    @Override
    public void updateEmail(int id, String email) {
        StringBuilder sql = new StringBuilder("UPDATE user AS U SET U.email = ");
        sql.append("'");
        sql.append(email);
        sql.append("'");
        sql.append(" WHERE U.id = " + id);
        Query query = entityManager.createNativeQuery(sql.toString());
        query.executeUpdate();
    }
}
