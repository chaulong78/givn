package com.msp.givn.dao.course;

import com.msp.givn.dto.CourseDTO;
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
public class CourseDTODaoImpl implements CourseDTODao{

    @Autowired
    private DataSource dataSource;

    @Override
    public List<CourseDTO> findAll() {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        CourseDTO courseDTO = null;
        List<CourseDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT C.id, C.name, CT.name AS type_name, U.full_name AS author_name, C.price, C.enabled  FROM course AS C JOIN user_detail AS U  ON C.author_id = U.user_id  JOIN course_type AS CT ON CT.id = C.type_id");
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setAuthorName(rs.getString("author_name"));
                courseDTO.setPrice(rs.getDouble("price"));
                courseDTO.setEnabled(rs.getBoolean("enabled"));

                list.add(courseDTO);
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

    @Override
    public CourseDTO findById(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        CourseDTO courseDTO = null;
        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT C.id, C.name, CT.name AS type_name, U.full_name AS author_name, C.price, C.enabled  FROM course AS C JOIN user_detail AS U  ON C.author_id = U.user_id  JOIN course_type AS CT ON CT.id = C.type_id WHERE C.id = ?");

            preState.setInt(1, id);
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setAuthorName(rs.getString("author_name"));
                courseDTO.setPrice(rs.getDouble("price"));
                courseDTO.setEnabled(rs.getBoolean("enabled"));
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

        return courseDTO;
    }
}
