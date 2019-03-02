package com.msp.givn.dao.post;

import com.msp.givn.dto.PostDTO;
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
public class PostDTODaoImpl implements PostDTODao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<PostDTO> findAll() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preState = null;
        ResultSet rs = null;

        PostDTO postDTO = null;
        List<PostDTO> list = new ArrayList<>();

        try {
            preState = connection.prepareCall(
                    "SELECT P.id, P.subject, U.full_name, PT.name AS type_name, P.create_date, P.enabled FROM post AS P JOIN user_detail U ON P.author_id = U.user_id JOIN post_type PT ON P.type_id = PT.id");

            rs = preState.executeQuery();

            while (rs.next()) {
                postDTO = new PostDTO();

                postDTO.setId(rs.getInt("id"));
                postDTO.setSubject(rs.getString("subject"));
                postDTO.setAuthorName(rs.getString("full_name"));
                postDTO.setTypeName(rs.getString("type_name"));
                postDTO.setCreateDate(rs.getDate("create_date"));
                postDTO.setEnabled(rs.getBoolean("enabled"));

                list.add(postDTO);
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
