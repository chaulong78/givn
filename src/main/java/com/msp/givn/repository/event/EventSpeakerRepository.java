package com.msp.givn.repository.event;

import com.msp.givn.entity.EventSpeaker;
import com.msp.givn.repository.event.custom.EventSpeakerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSpeakerRepository extends JpaRepository<EventSpeaker, Integer>, EventSpeakerRepositoryCustom {
}
