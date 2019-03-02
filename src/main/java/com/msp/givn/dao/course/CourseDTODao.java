package com.msp.givn.dao.course;

import com.msp.givn.dto.CourseDTO;

import java.util.List;

public interface CourseDTODao {

    List<CourseDTO> findAll();

    CourseDTO findById(int id);
}
