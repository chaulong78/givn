package com.msp.givn.repository.event.custom.impl;

import com.msp.givn.entity.EventSpeaker;
import com.msp.givn.repository.event.custom.EventSpeakerRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventSpeakerRepositoryCustomImpl implements EventSpeakerRepositoryCustom {

    @Autowired
    private DataSource dataSource;

    @Override
    public void saveList(List<Integer> speakerList, int eventId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for (Integer id : speakerList) {
                String sql = "INSERT INTO event_speaker (speaker_id, event_id) VALUES (" + id + ", " + eventId + ")";
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
    public void updateList(List<Integer> speakerList, int eventId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            String deleteSql = "DELETE FROM event_speaker WHERE event_id = " + eventId;
            statement.addBatch(deleteSql);

            for (Integer id : speakerList) {
                String sql = "INSERT INTO event_speaker (speaker_id, event_id) VALUES (" + id + ", " + eventId + ")";
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
    public List<EventSpeaker> findAllES() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        EventSpeaker eventSpeaker = null;
        List<EventSpeaker> speakerList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM event_speaker";
            statement = connection.prepareStatement(sql);

            rs = ((PreparedStatement) statement).executeQuery();

            while (rs.next()){
                eventSpeaker = new EventSpeaker();

                eventSpeaker.setSpeakerId(rs.getInt("speaker_id"));
                eventSpeaker.setEventId(rs.getInt("event_id"));

                speakerList.add(eventSpeaker);
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
