package com.msp.givn.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PasswordChangeDTO {

    private String oldPassword;

    private String password;

    private String passwordAgain;
}
