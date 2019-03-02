package com.msp.givn.repository.media;

import com.msp.givn.config.CustomDataSource;
import com.msp.givn.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ImageRepositoryCustomImpl implements ImageRepositoryCustom {

    @Autowired
    private CustomDataSource dataSource;

    @Override
    public void saveAllImage(List<String> urlList) {
        Connection connection = null;
        PreparedStatement preState = null;

        StringBuilder sql = new StringBuilder("INSERT INTO image (url) values ");

        try {
            connection = dataSource.getConnection();

            int size = urlList.size();
            for (int i = 0; i < size; i++) {
                sql.append("('");
                sql.append(urlList.get(i));

                if (i == size-1){
                    sql.append("')");
                } else {
                    sql.append("'),");
                }
            }

            preState = connection.prepareCall(sql.toString());
            preState.executeUpdate();

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
    }

    @Override
    public String findUrlById(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall("SELECT url FROM image WHERE id = ?");
            preState.setInt(1, id);

            rs = preState.executeQuery();

            if (rs.next()){
                String url = rs.getString("url");
                return url;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
