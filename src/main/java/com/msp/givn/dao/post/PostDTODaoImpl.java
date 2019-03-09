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
        PreparedStatement preState = null;
        ResultSet rs = null;

        PostDTO postDTO = null;
        List<PostDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT P.id, P.name, P.content, P.image, P.url_name, U.full_name, PT.name AS type_name, P.create_date, P.enabled FROM post AS P JOIN user_detail U ON P.author_id = U.user_id JOIN post_type PT ON P.type_id = PT.id");

            rs = preState.executeQuery();

            while (rs.next()) {
                postDTO = new PostDTO();

                postDTO.setId(rs.getInt("id"));
                postDTO.setName(rs.getString("name"));
                postDTO.setContent(rs.getString("content"));
                postDTO.setUrlName(rs.getString("url_name"));
                postDTO.setImage(rs.getString("image"));
                postDTO.setAuthorName("Get Ins");
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

    @Override
    public List<PostDTO> findAllWithOutContent() {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        PostDTO postDTO = null;
        List<PostDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preState = connection.prepareCall(
                    "SELECT P.id, P.name, P.url_name, P.description, P.image,  U.full_name, PT.name AS type_name, " +
                            "P.create_date, P.enabled, PT.url_name AS type_url " +
                            "FROM post AS P JOIN user_detail U ON P.author_id = U.user_id " +
                            "JOIN post_type PT ON P.type_id = PT.id WHERE P.enabled = 1");

            rs = preState.executeQuery();

            while (rs.next()) {
                postDTO = new PostDTO();

                postDTO.setId(rs.getInt("id"));
                postDTO.setName(rs.getString("name"));
                postDTO.setUrlName(rs.getString("url_name"));
                postDTO.setDescription(rs.getString("description"));
                postDTO.setImage(rs.getString("image"));
                postDTO.setAuthorName("Get Ins");
                postDTO.setTypeName(rs.getString("type_name"));
                postDTO.setTypeUrl(rs.getString("type_url"));
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

    @Override
    public List<PostDTO> findByTypeUrl(String url) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        PostDTO postDTO = null;
        List<PostDTO> list = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT P.id, P.name, P.url_name, P.description, P.image,  U.full_name, PT.name AS type_name, " +
                            "P.create_date, P.enabled, PT.url_name AS type_url " +
                            "FROM post AS P JOIN user_detail U ON P.author_id = U.user_id " +
                            "JOIN post_type PT ON P.type_id = PT.id WHERE P.enabled = 1 AND PT.url_name LIKE "
            );
            sql.append("'");
            sql.append("/danh-muc-tin/" + url);
            sql.append("'");

            preState = connection.prepareCall(sql.toString());
            rs = preState.executeQuery();

            while (rs.next()) {
                postDTO = new PostDTO();

                postDTO.setId(rs.getInt("id"));
                postDTO.setName(rs.getString("name"));
                postDTO.setUrlName(rs.getString("url_name"));
                postDTO.setDescription(rs.getString("description"));
                postDTO.setImage(rs.getString("image"));
                postDTO.setAuthorName("Get Ins");
                postDTO.setTypeName(rs.getString("type_name"));
                postDTO.setTypeUrl(rs.getString("type_url"));
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

    @Override
    public PostDTO findByUrlName(String urlName) {
        Connection connection = null;
        PreparedStatement preState = null;
        ResultSet rs = null;

        PostDTO postDTO = null;

        try {
            connection = dataSource.getConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT P.id, P.name, P.content, P.image, P.url_name," +
                            " U.full_name, PT.name AS type_name, P.create_date, P.enabled, PT.url_name AS type_url " +
                            "FROM post AS P JOIN user_detail U ON P.author_id = U.user_id JOIN post_type PT ON P.type_id = PT.id " +
                            "WHERE P.enabled = 1 AND P.url_name LIKE "
            );
            sql.append("'");
            sql.append(urlName);
            sql.append("'");

            preState = connection.prepareCall(sql.toString());

            rs = preState.executeQuery();

            while (rs.next()) {
                postDTO = new PostDTO();

                postDTO.setId(rs.getInt("id"));
                postDTO.setName(rs.getString("name"));
                postDTO.setContent(rs.getString("content"));
                postDTO.setUrlName(rs.getString("url_name"));
                postDTO.setImage(rs.getString("image"));
                postDTO.setAuthorName("Get Ins");
                postDTO.setTypeName(rs.getString("type_name"));
                postDTO.setTypeUrl(rs.getString("type_url"));
                postDTO.setCreateDate(rs.getDate("create_date"));
                postDTO.setEnabled(rs.getBoolean("enabled"));
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

        return postDTO;
    }
}
