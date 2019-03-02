package com.msp.givn.repository.user;

import com.msp.givn.entity.UserDetail;
import com.msp.givn.repository.user.custom.UserDetailRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>, UserDetailRepositoryCustom {

    UserDetail findById(int id);
}
