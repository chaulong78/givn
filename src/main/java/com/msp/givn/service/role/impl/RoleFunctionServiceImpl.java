package com.msp.givn.service.role.impl;

import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.entity.RoleFunction;
import com.msp.givn.repository.role.RoleFunctionRepository;
import com.msp.givn.service.role.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleFunctionServiceImpl implements RoleFunctionService {

    @Autowired
    private RoleFunctionRepository roleFunctionRepository;

    @Override
    public List<RoleFunction> findAllByRoleId(int id) {
        return roleFunctionRepository.findAllByRoleId(id);
    }

    @Override
    public void saveList(List<RoleFunction> roleFunctionList) {
        roleFunctionRepository.saveList(roleFunctionList);
    }

    @Override
    public void insertEmptyList(List<FunctionDTO> functionDTOList, int roleId) {
        roleFunctionRepository.insertEmptyList(functionDTOList, roleId);
    }

    @Override
    public void setFalseAllFunction(List<RoleFunction> roleFunctionList) {
        for (RoleFunction rf : roleFunctionList) {
            if (rf.isCanView()) {
                rf.setCanView(false);
            }
            if (rf.isCanCreate()) {
                rf.setCanCreate(false);
            }
            if (rf.isCanUpdate()) {
                rf.setCanUpdate(false);
            }
            if (rf.isCanDelete()) {
                rf.setCanDelete(false);
            }
        }
    }

    @Override
    public void updateFunction(List<RoleFunction> roleFunctionList, List<String> dataList, int type) {
        if (dataList != null) {
            switch (type) {
                case 1:
                    for (RoleFunction rf : roleFunctionList) {
                        for (String data : dataList) {
                            if (data.equals(rf.getFunctionId())) {
                                rf.setCanView(true);
                            }
                        }
                    }
                    break;
                case 2:
                    for (RoleFunction rf : roleFunctionList) {
                        for (String data : dataList) {
                            if (data.equals(rf.getFunctionId())) {
                                rf.setCanCreate(true);
                            }
                        }
                    }
                    break;
                case 3:
                    for (RoleFunction rf : roleFunctionList) {
                        for (String data : dataList) {
                            if (data.equals(rf.getFunctionId())) {
                                rf.setCanUpdate(true);
                            }
                        }
                    }
                    break;
                case 4:
                    for (RoleFunction rf : roleFunctionList) {
                        for (String data : dataList) {
                            if (data.equals(rf.getFunctionId())) {
                                rf.setCanDelete(true);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void updateAllFunction(List<RoleFunction> roleFunctionList, List<String> views, List<String> creates, List<String> updates, List<String> deletes) {
        updateFunction(roleFunctionList, views, 1);
        updateFunction(roleFunctionList, creates, 2);
        updateFunction(roleFunctionList, updates, 3);
        updateFunction(roleFunctionList, deletes, 4);
    }
}
