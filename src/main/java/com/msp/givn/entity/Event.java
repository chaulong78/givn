package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "url_name")
    private String urlName;

    @Column(name = "event_time")
    private String eventTime;

    @Column(name = "event_place")
    private String eventPlace;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "map")
    private String map;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "event_speaker",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id", referencedColumnName = "id"))
    private List<Speaker> speakers;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", eventTime=" + eventTime +
                ", eventPlace='" + eventPlace + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
