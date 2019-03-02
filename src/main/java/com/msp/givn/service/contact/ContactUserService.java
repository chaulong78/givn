package com.msp.givn.service.contact;

import com.msp.givn.entity.ContactUser;

import java.util.List;

public interface ContactUserService {

    List<ContactUser> findAll();

    void save(ContactUser contactUser);

    void deleteById(int id);
}
