package com.msp.givn.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class ClassRoomDTO {

    private int id;

    private String courseName;

    private String teacherName;

    private String name;

    private String description;

    private int studentNumber;

    private Date beginDate;

    private Date endDate;

    private boolean enabled;
}
