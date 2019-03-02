package com.msp.givn.repository.course;

import com.msp.givn.entity.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {

    CourseType findById(int id);

    CourseType findByName(String name);

    void deleteById(int id);

    CourseType save(CourseType type);
}
