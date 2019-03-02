package com.msp.givn.service.classroom.impl;

import com.msp.givn.dao.classroom.ClassRoomDTODao;
import com.msp.givn.dto.ClassRoomDTO;
import com.msp.givn.service.classroom.ClassRoomDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomDTOServiceImpl implements ClassRoomDTOService {

    @Autowired
    private ClassRoomDTODao classRoomDTODao;

    @Override
    public List<ClassRoomDTO> findAll() {
        return classRoomDTODao.findAll();
    }
}
