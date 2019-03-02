package com.msp.givn.dao.function;

import com.msp.givn.dto.FunctionAuthorizeDTO;

import java.util.List;

public interface FunctionAuthorizeDTODao {

    List<FunctionAuthorizeDTO> findAllByRoleId(int id);
}
