package com.msp.givn.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class ProfileDTO {

    private int id;

    private String userName;

    private String email;

    private String avatar;

    private String fullName;

    private Date birthDate;

    private boolean gender;

    private String address;

    private String phone;

    private String job;
}
