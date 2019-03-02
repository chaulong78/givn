package com.msp.givn.repository.course;

import com.msp.givn.entity.Course;
import com.msp.givn.repository.course.custom.CourseRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, CourseRepositoryCustom {

    Course findById(int id);

    List<Course> findByAuthorId(int id);

    List<Course> findByTypeId(int id);

    void deleteById(int id);
}
