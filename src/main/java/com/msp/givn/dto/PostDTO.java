package com.msp.givn.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PostDTO {

    private int id;

    private String authorName;

    private String typeName;

    private String subject;

    private Date createDate;

    private boolean enabled;
}
