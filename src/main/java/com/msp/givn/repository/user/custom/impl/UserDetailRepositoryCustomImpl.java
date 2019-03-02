package com.msp.givn.repository.user.custom.impl;

import com.msp.givn.entity.UserDetail;
import com.msp.givn.repository.user.custom.UserDetailRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDetailRepositoryCustomImpl implements UserDetailRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public String findNameById(int id) {
        Query query = entityManager.createNativeQuery("SELECT UD.full_name FROM user_detail AS UD WHERE user_id = ?");
        query.setParameter(1, id);
        return (String) query.getSingleResult();
    }

    @Override
    public List<UserDetail> findAllByRole(String roleName) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        UserDetail userDetail = null;
        List<UserDetail> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT U.user_id AS id, U.full_name FROM user_detail AS U " +
                            "JOIN user_role AS UR ON U.user_id = UR.user_id JOIN role AS R ON R.id = UR.role_id WHERE R.name LIKE ");
            sql.append("'");
            sql.append(roleName);
            sql.append("' OR R.name LIKE ");
            sql.append("'");
            sql.append("ROLE_ADMIN");
            sql.append("'");
            preState = connection.prepareStatement(sql.toString());

            rs = preState.executeQuery();

            while (rs.next()) {
                userDetail = new UserDetail();
                userDetail.setId(rs.getInt("id"));
                userDetail.setFullName(rs.getString("full_name"));

                list.add(userDetail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (preState != null) {
                    preState.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
