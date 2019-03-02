package com.msp.givn.repository.user;

import com.msp.givn.entity.UserRole;
import com.msp.givn.repository.user.custom.UserRoleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, UserRoleRepositoryCustom {
}
