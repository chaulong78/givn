package com.msp.givn.service.function.impl;

import com.msp.givn.dao.function.FunctionAuthorizeDTODao;
import com.msp.givn.dto.FunctionAuthorizeDTO;
import com.msp.givn.service.function.FunctionAuthorizeDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionAuthorizeDTOServiceImpl implements FunctionAuthorizeDTOService {

    @Autowired
    private FunctionAuthorizeDTODao functionAuthorizeDTODao;

    @Override
    public List<FunctionAuthorizeDTO> findAllByRoleId(int id) {
        return functionAuthorizeDTODao.findAllByRoleId(id);
    }
}
