package com.msp.givn.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
@Data
public class UserRole {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
}
