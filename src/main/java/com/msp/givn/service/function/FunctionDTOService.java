package com.msp.givn.service.function;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dto.FunctionDTO;

import java.util.List;

public interface FunctionDTOService {

    List<FunctionDTO> findByRoleId(int id);

    void updateFunctionDTOForUsers(List<FunctionDTO> functionDTOList, List<CustomUserDetail> editedUserList);
}
