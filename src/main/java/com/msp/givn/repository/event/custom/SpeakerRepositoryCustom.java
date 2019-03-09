package com.msp.givn.repository.event.custom;

import com.msp.givn.entity.Speaker;

import java.util.List;

public interface SpeakerRepositoryCustom {

    List<Speaker> findByEventId(int id);
}
