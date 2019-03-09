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
    public List<CourseDTO> findByTypeUrl(String url) {
        return courseDTODao.findByTypeUrl(url);
    }

    @Override
    public List<CourseDTO> find3Other() {
        return courseDTODao.find3Other();
    }

    @Override
    public List<CourseDTO> findMultiByUrlName(String url) {
        return courseDTODao.findMultiByUrlName(url);
    }

    @Override
    public CourseDTO findByUrlName(String urlName) {
        return courseDTODao.findByUrlName(urlName);
    }

    @Override
    public CourseDTO findById(int id) {
        return courseDTODao.findById(id);
    }
}
