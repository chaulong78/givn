package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_function")
@Data
public class RoleFunction {

    @Id
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "function_id")
    private String functionId;

    @Column(name = "can_view")
    private boolean canView;

    @Column(name = "can_create")
    private boolean canCreate;

    @Column(name = "can_update")
    private boolean canUpdate;

    @Column(name = "can_delete")
    private boolean canDelete;
}
