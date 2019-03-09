package com.msp.givn.service.course;

import com.msp.givn.dto.CourseDTO;

import java.util.List;

public interface CourseDTOService {

    List<CourseDTO> findAll();

    List<CourseDTO> findByTypeUrl(String url);

    List<CourseDTO> find3Other();

    List<CourseDTO> findMultiByUrlName(String url);

    CourseDTO findByUrlName(String urlName);

    CourseDTO findById(int id);
}
