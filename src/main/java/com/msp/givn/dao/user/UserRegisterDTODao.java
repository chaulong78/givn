package com.msp.givn.dao.user;

import com.msp.givn.entity.User;

import java.util.List;

public interface UserRegisterDTODao {

    List<User> findByUsernameAndEmail(String username, String email);
}
