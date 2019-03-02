package com.msp.givn.dao.function.impl;

import com.msp.givn.dao.function.FunctionDTODao;
import com.msp.givn.dto.FunctionDTO;
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
public class FunctionDTODaoImpl implements FunctionDTODao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<FunctionDTO> findByRoleId(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        FunctionDTO functionDTO = null;
        List<FunctionDTO> functionDTOList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT f.*, rf.can_view, rf.can_create, rf.can_update, rf.can_delete " +
                            "FROM app_function AS f " +
                            "JOIN role_function AS rf " +
                            "ON f.id = rf.function_id " +
                            "JOIN role AS r " +
                            "ON r.id = rf.role_id " +
                            "WHERE r.id = ?;");
            preState.setInt(1, id);
            rs = preState.executeQuery();

            while (rs.next()) {
                functionDTO = new FunctionDTO();

                functionDTO.setId(rs.getString("id"));
                functionDTO.setName(rs.getString("name"));
                functionDTO.setUrl(rs.getString("url"));
                functionDTO.setIcon(rs.getString("icon"));
                functionDTO.setParentId(rs.getString("parent_id"));

                functionDTO.setCanView(rs.getBoolean("can_view"));
                functionDTO.setCanCreate(rs.getBoolean("can_create"));
                functionDTO.setCanUpdate(rs.getBoolean("can_update"));
                functionDTO.setCanDelete(rs.getBoolean("can_delete"));

                functionDTOList.add(functionDTO);
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

        return functionDTOList;
    }
}
