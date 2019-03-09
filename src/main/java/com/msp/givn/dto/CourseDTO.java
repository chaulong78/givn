package com.msp.givn.dto;

import lombok.Data;

@Data
public class CourseDTO {

    private int id;

    private String name;

    private String urlName;

    private String description;

    private String image;

    private String content;

    private String requirement;

    private int duration;

    private String videoUrl;

    private String typeName;

    private String typeUrl;

    private String authorName;

    private int rating;

    private String price;

    private boolean enabled;
}
