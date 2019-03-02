package com.msp.givn.service.course.impl;

import com.msp.givn.entity.Course;
import com.msp.givn.repository.course.CourseRepository;
import com.msp.givn.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findByAuthorId(int id) {
        return courseRepository.findByAuthorId(id);
    }

    @Override
    public List<Course> findByTypeId(int id) {
        return courseRepository.findByTypeId(id);
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public String findNameById(int id) {
        return courseRepository.findNameById(id);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void updateList(List<Course> list) {
        courseRepository.updateList(list);
    }

    @Override
    public void delete(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findIdAndName() {
        return courseRepository.findIdAndName();
    }

    @Override
    public void updateTypeForCourse(int id) {
        List<Course> courseList = findByTypeId(id);
        if (courseList != null) {
            for (Course c : courseList) {
                c.setTypeId(1);
            }
            updateList(courseList);
        }
    }
}
