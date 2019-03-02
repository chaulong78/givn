package com.msp.givn.dao.user.impl;

import com.msp.givn.dao.user.AccountDTODao;
import com.msp.givn.dto.AccountDTO;
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
public class AccountDTODaoImpl implements AccountDTODao {
    @Autowired
    private DataSource dataSource;

    @Override
    public List<AccountDTO> findAll() {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        AccountDTO accountDTO = null;
        List<AccountDTO> accountDTOList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT U.id, U.user_name, U.email, U.enabled, R.name AS role_name FROM user AS U JOIN user_role AS UR ON U.id = UR.user_id JOIN role AS R ON UR.role_id = R.id");

            rs = preState.executeQuery();

            while (rs.next()) {
                accountDTO = new AccountDTO();

                accountDTO.setId(rs.getInt("id"));
                accountDTO.setUsername(rs.getString("user_name"));
                accountDTO.setEmail(rs.getString("email"));
                accountDTO.setEnabled(rs.getBoolean("enabled"));
                accountDTO.setRoleName(rs.getString("role_name"));

                accountDTOList.add(accountDTO);
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

        return accountDTOList;
    }

    @Override
    public AccountDTO findById(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        AccountDTO accountDTO = null;

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT U.id, U.user_name, U.email, U.enabled, R.name AS role_name FROM user AS U " +
                            "JOIN user_role AS UR " +
                            "ON U.id = UR.user_id " +
                            "JOIN role AS R ON UR.role_id = R.id WHERE U.id = ?");

            preState.setInt(1, id);
            rs = preState.executeQuery();

            while (rs.next()) {
                accountDTO = new AccountDTO();

                accountDTO.setId(rs.getInt("id"));
                accountDTO.setUsername(rs.getString("user_name"));
                accountDTO.setEmail(rs.getString("email"));
                accountDTO.setEnabled(rs.getBoolean("enabled"));
                accountDTO.setRoleName(rs.getString("role_name"));
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

        return accountDTO;
    }
}
