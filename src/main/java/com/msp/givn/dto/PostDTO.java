package com.msp.givn.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PostDTO {

    private int id;

    private String authorName;

    private String name;

    private String description;

    private String image;

    private String content;

    private String urlName;

    private String typeName;

    private String typeUrl;

    private Date createDate;

    private boolean enabled;
}
