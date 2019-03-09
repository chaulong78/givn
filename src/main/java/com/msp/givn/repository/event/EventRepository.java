package com.msp.givn.repository.event;

import com.msp.givn.entity.Event;
import com.msp.givn.repository.event.custom.EventRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>, EventRepositoryCustom {

    List<Event> findAll();

    Event findById(int id);

    Event findByUrlName(String urlName);

    void deleteById(int id);
}
