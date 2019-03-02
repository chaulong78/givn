package com.msp.givn.dto;

import lombok.Data;

@Data
public class CourseDTO {

    private int id;

    private String name;

    private String typeName;

    private String authorName;

    private Double price;

    private boolean enabled;
}
