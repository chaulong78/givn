package com.msp.givn.service.course.impl;

import com.msp.givn.entity.CourseType;
import com.msp.givn.repository.course.CourseTypeRepository;
import com.msp.givn.service.course.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Override
    public List<CourseType> findAll() {
        return courseTypeRepository.findAll();
    }

    @Override
    public CourseType findById(int id) {
        return courseTypeRepository.findById(id);
    }

    @Override
    public CourseType findByName(String name) {
        return courseTypeRepository.findByName(name);
    }

    @Override
    public void deleteById(int id) {
        courseTypeRepository.deleteById(id);
    }

    @Override
    public CourseType save(CourseType type) {
        return courseTypeRepository.save(type);
    }
}
