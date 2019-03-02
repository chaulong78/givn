package com.msp.givn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionDTO {

    private String id;

    private String name;

    private String url;

    private String icon;

    private String parentId;

    private boolean canCreate;

    private boolean canView;

    private boolean canUpdate;

    private boolean canDelete;

    private boolean canChange;

    private boolean canRegister;

    private boolean canCancel;
}
