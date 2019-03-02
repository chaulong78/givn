package com.msp.givn.service.user;

import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.entity.UserDetail;

import java.util.List;

public interface UserDetailService {

    UserDetail findById(int id);

    List<UserDetail> findAll();

    String findNameById(int id);

    UserDetail save(UserDetail userDetail);

    List<UserDetail> findAllByRole(String roleName);

    void update(ProfileDTO profileDTO, int userId);

    UserDetail createDetailForUser(int userId);
}
