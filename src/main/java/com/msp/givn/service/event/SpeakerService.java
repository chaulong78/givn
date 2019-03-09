package com.msp.givn.service.event;

import com.msp.givn.entity.Speaker;

import java.util.List;

public interface SpeakerService {

    List<Speaker> findAll();

    Speaker findById(int id);

    List<Speaker> findByEvent(int eventId);

    void deleteById(int id);

    void save(Speaker speaker);
}
