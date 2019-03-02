package com.msp.givn.dao.function.impl;

import com.msp.givn.dao.function.FunctionAuthorizeDTODao;
import com.msp.givn.dto.FunctionAuthorizeDTO;
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
public class FunctionAuthorizeDTODaoImpl implements FunctionAuthorizeDTODao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<FunctionAuthorizeDTO> findAllByRoleId(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        FunctionAuthorizeDTO functionAuthorizeDTO = null;
        List<FunctionAuthorizeDTO> functionAuthorizeDTOList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT F.id, F.name, RF.can_view, RF.can_create, RF.can_update, RF.can_delete " +
                            "FROM role_function AS RF " +
                            "JOIN app_function AS F " +
                            "ON F.id = RF.function_id " +
                            "WHERE RF.role_id = ?");

            preState.setInt(1, id);
            rs = preState.executeQuery();

            while (rs.next()) {
                functionAuthorizeDTO = new FunctionAuthorizeDTO();

                functionAuthorizeDTO.setFunctionId(rs.getString("id"));
                functionAuthorizeDTO.setFunctionName((rs.getString("name")));
                functionAuthorizeDTO.setCanView(rs.getBoolean("can_view"));
                functionAuthorizeDTO.setCanCreate(rs.getBoolean("can_create"));
                functionAuthorizeDTO.setCanUpdate(rs.getBoolean("can_update"));
                functionAuthorizeDTO.setCanDelete(rs.getBoolean("can_delete"));

                functionAuthorizeDTOList.add(functionAuthorizeDTO);
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

        return functionAuthorizeDTOList;
    }
}
