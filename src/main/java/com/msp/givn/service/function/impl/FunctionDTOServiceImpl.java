package com.msp.givn.service.function.impl;

import com.msp.givn.config.CustomUserDetail;
import com.msp.givn.dao.function.FunctionDTODao;
import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.service.function.FunctionDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FunctionDTOServiceImpl implements FunctionDTOService {

    @Autowired
    private FunctionDTODao functionDTODao;

    @Override
    @Transactional
    public List<FunctionDTO> findByRoleId(int id) {
        return functionDTODao.findByRoleId(id);
    }

    @Override
    public void updateFunctionDTOForUsers(List<FunctionDTO> functionDTOList, List<CustomUserDetail> editedUserList) {
        for (CustomUserDetail user : editedUserList) {
            user.setFunctionDTOList(functionDTOList);
        }
    }
}
