package com.msp.givn.service.event.impl;

import com.msp.givn.entity.EventSpeaker;
import com.msp.givn.repository.event.EventSpeakerRepository;
import com.msp.givn.service.event.EventSpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventSpeakerServiceImpl implements EventSpeakerService {

    @Autowired
    private EventSpeakerRepository eventSpeakerRepository;

    @Override
    public List<EventSpeaker> findAllES() {
        return eventSpeakerRepository.findAllES();
    }
}
