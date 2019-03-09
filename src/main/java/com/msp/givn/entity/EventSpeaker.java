package com.msp.givn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_speaker")
@Data
public class EventSpeaker {

    @Id
    @Column(name = "speaker_id")
    private int speakerId;

    @Column(name = "event_id")
    private int eventId;

    @Override
    public String toString() {
        return "EventSpeaker{" +
                "speakerId=" + speakerId +
                ", eventId=" + eventId +
                '}';
    }
}
