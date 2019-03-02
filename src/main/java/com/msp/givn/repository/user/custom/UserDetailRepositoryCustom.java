package com.msp.givn.repository.user.custom;

import com.msp.givn.entity.UserDetail;

import java.util.List;

public interface UserDetailRepositoryCustom {

    String findNameById(int id);

    List<UserDetail> findAllByRole(String roleName);
}
