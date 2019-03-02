package com.msp.givn.dao.function;

import com.msp.givn.dto.FunctionDTO;

import java.util.List;

public interface FunctionDTODao {

    List<FunctionDTO> findByRoleId(int id);
}
