package com.msp.givn.dao.classroom;

import com.msp.givn.dto.ClassRoomDTO;
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
public class ClassRoomDTODaoImpl implements ClassRoomDTODao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<ClassRoomDTO> findAll() {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        ClassRoomDTO classRoomDTO = null;
        List<ClassRoomDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT CL.id, C.name AS course_name, U.full_name as teacher_name, CL.name AS class_name, CL.student_number, CL.description, CL.begin_date, CL.end_date, CL.enabled FROM class AS CL JOIN course AS C ON CL.course_id = C.id JOIN user_detail AS U ON CL.teacher_id = U.user_id");

            rs = preState.executeQuery();

            while (rs.next()) {
                classRoomDTO = new ClassRoomDTO();

                classRoomDTO.setId(rs.getInt("id"));
                classRoomDTO.setCourseName(rs.getString("course_name"));
                classRoomDTO.setTeacherName(rs.getString("teacher_name"));
                classRoomDTO.setName(rs.getString("class_name"));
                classRoomDTO.setDescription(rs.getString("description"));
                classRoomDTO.setStudentNumber(rs.getInt("student_number"));
                classRoomDTO.setBeginDate(rs.getDate("begin_date"));
                classRoomDTO.setEndDate(rs.getDate("end_date"));
                classRoomDTO.setEnabled(rs.getBoolean("enabled"));

                list.add(classRoomDTO);
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
