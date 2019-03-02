package com.msp.givn.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FunctionAuthorizeDTO {

    @NotNull
    @NotEmpty
    private String functionId;

    @NotNull
    @NotEmpty
    private String functionName;

    private boolean canView;

    private boolean canCreate;

    private boolean canUpdate;

    private boolean canDelete;

    private boolean canChange;

    private boolean canRegister;

    private boolean canCancel;
}
