package com.msp.givn.repository.role;

import com.msp.givn.entity.RoleFunction;
import com.msp.givn.repository.role.custom.RoleFunctionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleFunctionRepository extends JpaRepository<RoleFunction, Integer>, RoleFunctionRepositoryCustom {

    List<RoleFunction> findByRoleId(int id);
}
