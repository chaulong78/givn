package com.msp.givn.service.course.impl;

import com.msp.givn.dao.course.CourseDTODao;
import com.msp.givn.dto.CourseDTO;
import com.msp.givn.service.course.CourseDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDTOServiceImpl implements CourseDTOService {

    @Autowired
    private CourseDTODao courseDTODao;

    @Override
    public List<CourseDTO> findAll() {
        return courseDTODao.findAll();
    }

    @Override
    public CourseDTO findById(int id) {
        return courseDTODao.findById(id);
    }
}
