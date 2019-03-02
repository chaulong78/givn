package com.msp.givn.service.course;

import com.msp.givn.dto.CourseDTO;

import java.util.List;

public interface CourseDTOService {

    List<CourseDTO> findAll();

    CourseDTO findById(int id);

}
