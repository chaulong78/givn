package com.msp.givn.repository.classroom;

import com.msp.givn.entity.ClassRoom;
import com.msp.givn.repository.classroom.custom.ClassRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer>, ClassRoomRepositoryCustom {

    ClassRoom findById(int id);

    ClassRoom findByName(String name);

    List<ClassRoom> findByTeacherId(int id);

    List<ClassRoom> findByCourseId(int id);

    void deleteById(int id);
}
