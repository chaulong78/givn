package com.msp.givn.repository.event.custom.impl;

import com.msp.givn.entity.Event;
import com.msp.givn.repository.event.custom.EventRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Event> findAllDesc() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        Event event = null;
        List<Event> eventList = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM event WHERE enabled = 1 ORDER BY event_time DESC";
            statement = connection.prepareStatement(sql);

            rs = ((PreparedStatement) statement).executeQuery();

            while (rs.next()){
                event = new Event();

                event.setId(rs.getInt("id"));
                event.setImage(rs.getString("image"));
                event.setName(rs.getString("name"));
                event.setUrlName(rs.getString("url_name"));
                event.setEventTime(rs.getString("event_time"));
                event.setEventPlace(rs.getString("event_place"));
                event.setDescription(rs.getString("description"));
                event.setContent(rs.getString("content"));
                event.setMap(rs.getString("map"));

                eventList.add(event);
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

        return eventList;
    }

    @Override
    public Event getNewestEvent() {
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet rs = null;
        Event event = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareCall(
                    "{call `getinsvn`.`getNewestEvent`()}");

            rs = statement.executeQuery();

            while (rs.next()){
                event = new Event();

                event.setId(rs.getInt("id"));
                event.setImage(rs.getString("image"));
                event.setName(rs.getString("name"));
                event.setUrlName(rs.getString("url_name"));
                event.setEventTime(rs.getString("event_time"));
                event.setEventPlace(rs.getString("event_place"));
                event.setDescription(rs.getString("description"));
                event.setContent(rs.getString("content"));
                event.setMap(rs.getString("map"));
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

        return event;
    }
}
