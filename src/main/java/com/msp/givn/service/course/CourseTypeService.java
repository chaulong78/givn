package com.msp.givn.service.course;

import com.msp.givn.entity.CourseType;

import java.util.List;

public interface CourseTypeService {

    List<CourseType> findAll();

    CourseType findById(int id);

    CourseType findByName(String name);

    void deleteById(int id);

    CourseType save(CourseType type);
}
