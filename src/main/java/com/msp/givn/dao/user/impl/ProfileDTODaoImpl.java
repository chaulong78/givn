package com.msp.givn.dao.user.impl;

import com.msp.givn.dao.user.ProfileDTODao;
import com.msp.givn.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProfileDTODaoImpl implements ProfileDTODao {

    @Autowired
    private DataSource dataSource;


    @Override
    public ProfileDTO findById(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        ProfileDTO profileDTO = null;

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT U.user_name, U.email, UD.full_name, UD.birth_date, UD.gender, UD.address, UD.phone, UD.job FROM user AS U JOIN user_detail AS UD ON U.id = UD.user_id WHERE U.id = ?;");

            preState.setInt(1, id);
            rs = preState.executeQuery();

            while (rs.next()) {
                profileDTO = new ProfileDTO();

                profileDTO.setUserName(rs.getString("user_name"));
                profileDTO.setEmail(rs.getString("email"));
                profileDTO.setFullName(rs.getString("full_name"));
                profileDTO.setBirthDate(rs.getDate("birth_date"));
                profileDTO.setGender(rs.getBoolean("gender"));
                profileDTO.setAddress(rs.getString("address"));
                profileDTO.setPhone(rs.getString("phone"));
                profileDTO.setJob(rs.getString("job"));
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

        return profileDTO;
    }
}
