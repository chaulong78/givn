package com.msp.givn.repository.course.custom;

import com.msp.givn.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepositoryCustomImpl implements CourseRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public String findNameById(int id) {
        Query query = entityManager.createNativeQuery("SELECT C.name FROM course AS C WHERE C.id = ?");
        query.setParameter(1, id);

        return (String) query.getSingleResult();
    }

    @Override
    public void updateList(List<Course> list) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for (Course course : list) {
                String sql = "UPDATE course SET author_id = " + course.getAuthorId() + " WHERE id = " + course.getId();
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
    public List<Course> findIdAndName() {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        Course course = null;
        List<Course> courseList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall("SELECT C.id, C.name FROM course AS C");

            rs = preState.executeQuery();

            while (rs.next()){
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));

                courseList.add(course);
            }

        } catch (SQLException e) {
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

        return courseList;
    }
}
