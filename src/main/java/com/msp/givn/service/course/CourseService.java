package com.msp.givn.service.course;

import com.msp.givn.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CourseService {

    List<Course> findAll();

    List<Course> findByAuthorId(int id);

    List<Course> findByTypeId(int id);

    Course findById(int id);

    String findNameById(int id);

    Course save(Course course);

    void updateList(List<Course> list);

    void delete(int id);

    List<Course> findIdAndName();

    void updateTypeForCourse(int id);
}
