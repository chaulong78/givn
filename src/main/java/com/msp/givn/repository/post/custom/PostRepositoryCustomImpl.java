package com.msp.givn.repository.post.custom;

import com.msp.givn.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

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
}
