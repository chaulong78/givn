package com.msp.givn.service.function;

import com.msp.givn.dto.FunctionAuthorizeDTO;

import java.util.List;

public interface FunctionAuthorizeDTOService {

    List<FunctionAuthorizeDTO> findAllByRoleId(int id);
}
