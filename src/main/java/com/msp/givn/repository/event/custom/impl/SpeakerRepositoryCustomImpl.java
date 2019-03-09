package com.msp.givn.repository.event.custom.impl;

import com.msp.givn.entity.Speaker;
import com.msp.givn.repository.event.custom.SpeakerRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SpeakerRepositoryCustomImpl implements SpeakerRepositoryCustom {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Speaker> findByEventId(int id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        Speaker speaker = null;
        List<Speaker> speakerList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM speaker AS S " +
                    "JOIN event_speaker es on S.id = es.speaker_id WHERE es.event_id = " + id;
            statement = connection.prepareStatement(sql);

            rs = ((PreparedStatement) statement).executeQuery();

            while (rs.next()) {
                speaker = new Speaker();

                speaker.setId(rs.getInt("id"));
                speaker.setName(rs.getString("name"));
                speaker.setImage(rs.getString("image"));
                speaker.setDescription(rs.getString("description"));
                speaker.setJob(rs.getString("job"));

                speakerList.add(speaker);
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

        return speakerList;
    }
}
