package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "course_type")
@Data
public class CourseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "url_name")
    private String urlName;

    @Column(name = "description")
    private String description;
}
