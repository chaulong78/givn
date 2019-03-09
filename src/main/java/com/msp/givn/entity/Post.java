package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "post")
@Data
public class Post {

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

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "content")
    private String content;

    @Column(name = "attach")
    private String attach;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "enabled")
    private boolean enabled;
}
