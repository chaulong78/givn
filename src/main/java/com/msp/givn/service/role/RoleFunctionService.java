package com.msp.givn.service.role;

import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.entity.RoleFunction;

import java.util.List;

public interface RoleFunctionService {

    List<RoleFunction> findAllByRoleId(int id);

    void saveList(List<RoleFunction> roleFunctionList);

    void insertEmptyList(List<FunctionDTO> functionDTOList, int roleId);

    void setFalseAllFunction(List<RoleFunction> roleFunctionList);

    void updateFunction(List<RoleFunction> roleFunctionList, List<String> dataList, int type);

    void updateAllFunction(List<RoleFunction> roleFunctionList,
                           List<String> views,
                           List<String> creates,
                           List<String> updates,
                           List<String> deletes);
}
