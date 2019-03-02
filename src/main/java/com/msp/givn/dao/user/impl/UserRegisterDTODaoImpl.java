package com.msp.givn.dao.user.impl;

import com.msp.givn.dao.user.UserRegisterDTODao;
import com.msp.givn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRegisterDTODaoImpl implements UserRegisterDTODao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<User> findByUsernameAndEmail(String username, String email) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        User user = null;
        List<User> userList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT U.id FROM user AS U WHERE user_name LIKE ? OR email LIKE ?");

            preState.setString(1, username);
            preState.setString(2, email);

            rs = preState.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));

                userList.add(user);
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

        return userList;
    }
}
