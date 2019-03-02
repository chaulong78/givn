package com.msp.givn.repository.classroom.custom;

import com.msp.givn.entity.ClassRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ClassRoomRepositoryCustomImpl implements ClassRoomRepositoryCustom {

    @Autowired
    private DataSource dataSource;

    @Override
    public void updateList(List<ClassRoom> list) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for (ClassRoom room : list) {
                String sql = "UPDATE course SET teacher_id = " + room.getTeacherId() + " WHERE id = " + room.getId();

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
}
