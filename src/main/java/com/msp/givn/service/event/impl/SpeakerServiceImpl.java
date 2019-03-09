package com.msp.givn.service.event.impl;

import com.msp.givn.entity.Speaker;
import com.msp.givn.repository.event.SpeakerRepository;
import com.msp.givn.service.event.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker findById(int id) {
        return speakerRepository.findById(id);
    }

    @Override
    public List<Speaker> findByEvent(int eventId) {
        return speakerRepository.findByEventId(eventId);
    }

    @Override
    public void deleteById(int id) {
        speakerRepository.deleteById(id);
    }

    @Override
    public void save(Speaker speaker) {
        speakerRepository.save(speaker);
    }
}
