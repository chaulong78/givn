package com.msp.givn.repository.event.custom;

import com.msp.givn.entity.Event;

import java.util.List;

public interface EventRepositoryCustom {

    List<Event> findAllDesc();

    Event getNewestEvent();
}
