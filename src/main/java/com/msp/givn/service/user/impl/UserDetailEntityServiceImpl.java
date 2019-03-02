package com.msp.givn.service.user.impl;

import com.msp.givn.dto.ProfileDTO;
import com.msp.givn.entity.UserDetail;
import com.msp.givn.repository.user.UserDetailRepository;
import com.msp.givn.service.user.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class UserDetailEntityServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public UserDetail findById(int id) {
        return userDetailRepository.findById(id);
    }

    @Override
    public List<UserDetail> findAll() {
        return userDetailRepository.findAll();
    }

    @Override
    public String findNameById(int id) {
        return userDetailRepository.findNameById(id);
    }

    @Override
    @Transactional
    public UserDetail save(UserDetail userDetail) {
        return userDetailRepository.save(userDetail);
    }

    @Override
    public List<UserDetail> findAllByRole(String roleName) {
        return userDetailRepository.findAllByRole(roleName);
    }

    @Override
    public void update(ProfileDTO profileDTO, int userId) {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(userId);
        userDetail.setFullName(profileDTO.getFullName());

        if (profileDTO.getBirthDate() == null || "null".equals(profileDTO.getBirthDate()) || "".equals(profileDTO.getBirthDate())) {
            userDetail.setBirthDate(Date.valueOf("2000-1-1"));
        } else {
            userDetail.setBirthDate(profileDTO.getBirthDate());
        }

        userDetail.setGender(profileDTO.isGender());
        userDetail.setPhone(profileDTO.getPhone());
        userDetail.setAddress(profileDTO.getAddress());
        userDetail.setJob(profileDTO.getJob());

        save(userDetail);
    }

    @Override
    public UserDetail createDetailForUser(int userId) {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(userId);
        return save(userDetail);
    }
}
