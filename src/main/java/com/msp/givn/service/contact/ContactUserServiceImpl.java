package com.msp.givn.service.contact;

import com.msp.givn.entity.ContactUser;
import com.msp.givn.repository.contact.ContactUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUserServiceImpl implements ContactUserService{

    @Autowired
    private ContactUserRepository contactUserRepository;

    @Override
    public List<ContactUser> findAll() {
        return contactUserRepository.findAll();
    }

    @Override
    public void save(ContactUser contactUser) {
        contactUserRepository.save(contactUser);
    }

    @Override
    public void deleteById(int id) {
        contactUserRepository.deleteById(id);
    }
}
