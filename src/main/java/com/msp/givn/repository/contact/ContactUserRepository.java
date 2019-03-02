package com.msp.givn.repository.contact;

import com.msp.givn.entity.ContactUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUserRepository extends JpaRepository<ContactUser, Integer> {

    void deleteById(int id);
}
