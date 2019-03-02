package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "post_type")
@Data
public class PostType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
