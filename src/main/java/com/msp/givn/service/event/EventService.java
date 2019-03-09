package com.msp.givn.service.event;

import com.msp.givn.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    List<Event> findAllDesc();

    Event findById(int id);

    Event findByUrlName(String urlName);

    Event getNewestEvent();

    void deleteById(int id);

    Event save(Event event);

    void saveSpeakerForEvent(String[] speakers, int eventId);

    void updateSpeakerForEvent(String[] speakers, int eventId);
}
