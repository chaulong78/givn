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
public class CourseDTODaoImpl implements CourseDTODao {

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
            preState = connection.prepareCall("SELECT C.id, C.name, C.url_name, C.description, C.image, C.content, C.video_url, C.requirement, C.duration, " +
                    "CT.name AS type_name, CT.url_name AS type_url, U.full_name AS author_name, C.price  " +
                    "FROM course AS C JOIN user_detail AS U ON C.author_id = U.user_id JOIN course_type AS CT ON CT.id = C.type_id " +
                    "WHERE C.enabled = 1");
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setUrlName(rs.getString("url_name"));
                courseDTO.setDescription(rs.getString("description"));
                courseDTO.setImage(rs.getString("image"));
                courseDTO.setContent(rs.getString("content"));
                courseDTO.setVideoUrl(rs.getString("video_url"));
                courseDTO.setRequirement(rs.getString("requirement"));
                courseDTO.setDuration(rs.getInt("duration"));
                courseDTO.setPrice(rs.getString("price"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setTypeUrl(rs.getString("type_url"));
                courseDTO.setAuthorName("Get Ins");

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
    public List<CourseDTO> findByTypeUrl(String url) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        CourseDTO courseDTO = null;
        List<CourseDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder("SELECT C.id, C.name, C.url_name, C.description, C.image, C.content, C.video_url, C.requirement, C.duration, " +
                    "CT.name AS type_name, CT.url_name AS type_url, U.full_name AS author_name, C.price  " +
                    "FROM course AS C JOIN user_detail AS U ON C.author_id = U.user_id JOIN course_type AS CT ON CT.id = C.type_id " +
                    "WHERE C.enabled = 1 AND CT.url_name LIKE ");
            sql.append("'");
            sql.append("/danh-muc/" + url);
            sql.append("'");

            preState = connection.prepareCall(sql.toString());
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setUrlName(rs.getString("url_name"));
                courseDTO.setDescription(rs.getString("description"));
                courseDTO.setImage(rs.getString("image"));
                courseDTO.setContent(rs.getString("content"));
                courseDTO.setVideoUrl(rs.getString("video_url"));
                courseDTO.setRequirement(rs.getString("requirement"));
                courseDTO.setDuration(rs.getInt("duration"));
                courseDTO.setPrice(rs.getString("price"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setTypeUrl(rs.getString("type_url"));
                courseDTO.setAuthorName("Get Ins");

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
    public List<CourseDTO> find3Other() {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        CourseDTO courseDTO = null;
        List<CourseDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall("SELECT C.id, C.name, C.url_name, C.description, C.image, C.duration, " +
                    "CT.name AS type_name, CT.url_name AS type_url, C.price  " +
                    "FROM course AS C JOIN user_detail AS U ON C.author_id = U.user_id JOIN course_type AS CT ON CT.id = C.type_id " +
                    "WHERE C.enabled = 1 LIMIT 3");
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setUrlName(rs.getString("url_name"));
                courseDTO.setDescription(rs.getString("description"));
                courseDTO.setImage(rs.getString("image"));
                courseDTO.setDuration(rs.getInt("duration"));
                courseDTO.setPrice(rs.getString("price"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setTypeUrl(rs.getString("type_url"));
                courseDTO.setAuthorName("Get Ins");

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
    public List<CourseDTO> findMultiByUrlName(String url) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        CourseDTO courseDTO = null;
        List<CourseDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder("SELECT C.id, C.name, C.url_name, C.description, C.image, C.content, C.video_url, C.requirement, C.duration, " +
                    "CT.name AS type_name, CT.url_name AS type_url, U.full_name AS author_name, C.price  " +
                    "FROM course AS C JOIN user_detail AS U ON C.author_id = U.user_id JOIN course_type AS CT ON CT.id = C.type_id " +
                    "WHERE C.enabled = 1 AND C.url_name LIKE ");
            sql.append("'%");
            sql.append(url);
            sql.append("%'");

            preState = connection.prepareCall(sql.toString());
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setUrlName(rs.getString("url_name"));
                courseDTO.setDescription(rs.getString("description"));
                courseDTO.setImage(rs.getString("image"));
                courseDTO.setContent(rs.getString("content"));
                courseDTO.setVideoUrl(rs.getString("video_url"));
                courseDTO.setRequirement(rs.getString("requirement"));
                courseDTO.setDuration(rs.getInt("duration"));
                courseDTO.setPrice(rs.getString("price"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setTypeUrl(rs.getString("type_url"));
                courseDTO.setAuthorName("Get Ins");

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
    public CourseDTO findByUrlName(String urlName) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        CourseDTO courseDTO = null;
        try {
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT C.id, C.name, C.url_name, C.description, C.image, C.content, C.video_url, C.requirement, C.duration, " +
                            "CT.name AS type_name, CT.url_name AS type_url, U.full_name AS author_name, C.price  " +
                            "FROM course AS C JOIN user_detail AS U ON C.author_id = U.user_id JOIN course_type AS CT ON CT.id = C.type_id " +
                            "WHERE C.enabled = 1 AND C.url_name LIKE ");
            sql.append("'");
            sql.append(urlName);
            sql.append("'");

            preState = connection.prepareCall(sql.toString());
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setUrlName(rs.getString("url_name"));
                courseDTO.setDescription(rs.getString("description"));
                courseDTO.setImage(rs.getString("image"));
                courseDTO.setContent(rs.getString("content"));
                courseDTO.setVideoUrl(rs.getString("video_url"));
                courseDTO.setRequirement(rs.getString("requirement"));
                courseDTO.setDuration(rs.getInt("duration"));
                courseDTO.setPrice(rs.getString("price"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setTypeUrl(rs.getString("type_url"));
                courseDTO.setAuthorName("Get Ins");
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

    @Override
    public CourseDTO findById(int id) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        CourseDTO courseDTO = null;
        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT C.id, C.name, C.url_name, CT.name AS type_name, U.full_name AS author_name, C.price, C.enabled  FROM course AS C JOIN user_detail AS U  ON C.author_id = U.user_id  JOIN course_type AS CT ON CT.id = C.type_id WHERE C.id = ?");

            preState.setInt(1, id);
            rs = preState.executeQuery();

            while (rs.next()) {
                courseDTO = new CourseDTO();

                courseDTO.setId(rs.getInt("id"));
                courseDTO.setName(rs.getString("name"));
                courseDTO.setUrlName(rs.getString("url_name"));
                courseDTO.setTypeName(rs.getString("type_name"));
                courseDTO.setAuthorName(rs.getString("author_name"));
                courseDTO.setPrice(rs.getString("price"));
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
