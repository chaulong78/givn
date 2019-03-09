package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "course")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "author_id")
    private int authorId;

    @Column(name = "type_id")
    private int typeId;

    @Column(name = "name")
    private String name;

    @Column(name = "url_name")
    private String urlName;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "price")
    private String price;

    @Column(name = "rating")
    private int rating;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "duration")
    private int duration;

    @Column(name = "enabled")
    private boolean enabled;

}
