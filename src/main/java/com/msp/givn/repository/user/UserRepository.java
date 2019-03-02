package com.msp.givn.repository.user;

import com.msp.givn.entity.User;
import com.msp.givn.repository.user.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

    User findByUserName(String userName);

    User findByEmail(String email);

    User findById(int id);

    void deleteById(int id);

    User findByIdAndEmail(int id, String email);
}
