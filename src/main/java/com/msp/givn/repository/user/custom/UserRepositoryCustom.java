package com.msp.givn.repository.user.custom;

public interface UserRepositoryCustom {

    String getPasswordById(int id);

    boolean updatePassword(int id, String password);

    void updateEnabled(int id, boolean enabled);

    void updateAvatar(int id, String avatar);

    void updateEmail(int id, String email);
}
