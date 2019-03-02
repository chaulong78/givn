package com.msp.givn.repository.course.custom;

import com.msp.givn.entity.Course;

import java.util.List;

public interface CourseRepositoryCustom {

    String findNameById(int id);

    void updateList(List<Course> list);

    List<Course> findIdAndName();
}
