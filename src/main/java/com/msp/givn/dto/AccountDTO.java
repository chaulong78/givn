package com.msp.givn.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountDTO {

    private int id;

    private String username;

    private String email;

    private String roleName;

    private boolean enabled;

    private Timestamp lastActive;
}
