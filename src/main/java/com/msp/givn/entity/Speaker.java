package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "speaker")
@Data
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "job")
    private String job;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "speakers", fetch = FetchType.EAGER)
    private List<Event> events;

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", job='" + job + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
