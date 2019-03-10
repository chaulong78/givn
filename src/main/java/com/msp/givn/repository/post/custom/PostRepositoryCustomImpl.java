package com.msp.givn.repository.post.custom;

import com.msp.givn.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    @Autowired
    private DataSource dataSource;

    @Override
    public void updateList(List<Post> list) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for (Post post : list) {
                String sql = "UPDATE post SET author id = " + post.getAuthorId() + " WHERE id = " + post.getId();

                statement.addBatch(sql);
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
    public List<Post> getNewestPost() {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet rs = null;
        Post post = null;
        List<Post> postList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareCall(
                    "{call `getinsvn`.`getNewestPost`()}");

            rs = statement.executeQuery();

            while (rs.next()){
                post = new Post();

                post.setName(rs.getString("name"));
                post.setUrlName(rs.getString("url_name"));
                post.setImage(rs.getString("image"));
                post.setCreateDate(rs.getDate("create_date"));
                post.setDescription(rs.getString("description"));

                postList.add(post);
            }

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

        return postList;
    }
}
