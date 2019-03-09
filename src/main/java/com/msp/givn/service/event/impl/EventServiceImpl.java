package com.msp.givn.service.event.impl;

import com.msp.givn.entity.Event;
import com.msp.givn.entity.Speaker;
import com.msp.givn.repository.event.EventRepository;
import com.msp.givn.repository.event.EventSpeakerRepository;
import com.msp.givn.repository.event.SpeakerRepository;
import com.msp.givn.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private EventSpeakerRepository eventSpeakerRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findAllDesc() {
        return eventRepository.findAllDesc();
    }

    @Override
    public Event findById(int id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event findByUrlName(String urlName) {
        return eventRepository.findByUrlName(urlName);
    }

    @Override
    public Event getNewestEvent() {
        return eventRepository.getNewestEvent();
    }

    @Override
    public void deleteById(int id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void saveSpeakerForEvent(String[] speakers, int eventId) {
        List<Integer> speakerIdList = new ArrayList<>();

        List<Speaker> speakerList = speakerRepository.findAll();
        int length = speakers.length;

        for (Speaker speaker : speakerList) {
            for (int i = 0; i < length; i++) {
                if (speaker.getId() == (Integer.valueOf(speakers[i].toString()))) {
                    speakerIdList.add(speaker.getId());
                    break;
                }
            }
        }

        if (speakerIdList.size() > 0) {
            eventSpeakerRepository.saveList(speakerIdList, eventId);
        }
    }

    @Override
    public void updateSpeakerForEvent(String[] speakers, int eventId) {
        List<Integer> speakerIdList = new ArrayList<>();

        List<Speaker> speakerList = speakerRepository.findAll();
        int length = speakers.length;

        for (Speaker speaker : speakerList) {
            for (int i = 0; i < length; i++) {
                if (speaker.getId() == (Integer.valueOf(speakers[i].toString()))) {
                    speakerIdList.add(speaker.getId());
                    break;
                }
            }
        }

        if (speakerIdList.size() > 0) {
            eventSpeakerRepository.updateList(speakerIdList, eventId);
        }
    }
}
