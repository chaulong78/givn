package com.msp.givn.repository.event.custom;

import com.msp.givn.entity.EventSpeaker;

import java.util.List;

public interface EventSpeakerRepositoryCustom {

    void saveList(List<Integer> speakerList, int eventId);

    void updateList(List<Integer> speakerList, int eventId);

    List<EventSpeaker> findAllES();
}
