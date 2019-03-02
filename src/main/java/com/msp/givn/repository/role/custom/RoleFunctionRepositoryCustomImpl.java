package com.msp.givn.repository.role.custom;

import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.entity.RoleFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleFunctionRepositoryCustomImpl implements RoleFunctionRepositoryCustom {

    @Autowired
    private DataSource dataSource;

    @Override
    public void saveList(List<RoleFunction> roleFunctionList) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for (RoleFunction rf : roleFunctionList) {
                StringBuilder sql = new StringBuilder();
                sql.append("UPDATE role_function " +
                        "SET can_view = " + rf.isCanView()
                        + ", can_create = " + rf.isCanCreate()
                        + ", can_update = " + rf.isCanUpdate()
                        + ", can_delete = " + rf.isCanDelete()
                        + " WHERE role_id = " + rf.getRoleId()
                        + " AND function_id LIKE ");
                sql.append("'");
                sql.append(rf.getFunctionId());
                sql.append("'");

                statement.addBatch(sql.toString());
            }

            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertEmptyList(List<FunctionDTO> functionDTOList, int roleId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for (FunctionDTO fun : functionDTOList) {
                StringBuilder sql = new StringBuilder();
                sql.append("INSERT INTO role_function "
                        + "(role_id, function_id, can_view, can_create, can_update, can_delete) "
                        + "VALUES " + "(" + roleId + ", ");

                sql.append("'");
                sql.append(fun.getId());
                sql.append("', ");
                sql.append("0, 0, 0, 0)");

                statement.addBatch(sql.toString());
            }

            statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<RoleFunction> findAllByRoleId(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        RoleFunction roleFunction = null;
        List<RoleFunction> roleFunctionList = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT RF.* FROM getinsvn.role_function AS RF WHERE RF.role_id = ?");

            preState.setInt(1, id);
            rs = preState.executeQuery();

            while (rs.next()) {
                roleFunction = new RoleFunction();

                roleFunction.setRoleId(rs.getInt("role_id"));
                roleFunction.setFunctionId((rs.getString("function_id")));
                roleFunction.setCanView(rs.getBoolean("can_view"));
                roleFunction.setCanCreate(rs.getBoolean("can_create"));
                roleFunction.setCanUpdate(rs.getBoolean("can_update"));
                roleFunction.setCanDelete(rs.getBoolean("can_delete"));

                roleFunctionList.add(roleFunction);
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

        return roleFunctionList;
    }
}
