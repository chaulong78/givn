package com.msp.givn.service.user;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.dto.UserRegisterDTO;
import com.msp.givn.entity.User;
import com.msp.givn.entity.UserDetail;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    User findById(int id);

    User findByIdAndEmail(int id, String email);

    List<User> findAll();

    String getPasswordById(int id);

    User save(User user);

    boolean updatePassword(int id, String password);

    void updateEnabled(int id, boolean enabled);

    void updateAvatar(int id, String avatar);

    void updateEmail(int id, String email);

    void deleteById(int id);

    void updateSessionAvatar(CustomUserDetail user, String avatar);

    void updateSessionEmail(CustomUserDetail user, String email);

    User createNewUser(UserRegisterDTO registerDTO);

    CustomUserDetail getCustomUserDetail();
}
