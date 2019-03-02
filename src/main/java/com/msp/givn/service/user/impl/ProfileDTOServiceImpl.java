package com.msp.givn.service.user.impl;

import com.msp.givn.dao.user.ProfileDTODao;
import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.service.user.ProfileDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileDTOServiceImpl implements ProfileDTOService {

    @Autowired
    private ProfileDTODao profileDTODao;

    @Override
    public ProfileDTO findById(int id) {
        return profileDTODao.findById(id);
    }
}
