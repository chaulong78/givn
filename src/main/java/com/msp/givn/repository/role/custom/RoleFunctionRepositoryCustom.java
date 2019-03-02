package com.msp.givn.repository.role.custom;

import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.entity.RoleFunction;

import java.util.List;

public interface RoleFunctionRepositoryCustom {

    void saveList(List<RoleFunction> roleFunctionList);

    /* Sau khi tạo role mới, insert function cho role đó - tất cả đề FALSE - truyền vào functionDTO đề lấy functionId */
    void insertEmptyList(List<FunctionDTO> functionDTOList, int roleId);

    List<RoleFunction> findAllByRoleId(int id);
}
