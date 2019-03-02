package com.msp.givn.service.classroom;

import com.msp.givn.entity.ClassRoom;

import java.util.List;

public interface ClassRoomService {

    ClassRoom findById(int id);

    ClassRoom findByName(String name);

    List<ClassRoom> findByTeacherId(int id);

    List<ClassRoom> findAll();

    List<ClassRoom> findByCourseId(int id);

    void deleteById(int id);

    ClassRoom save(ClassRoom classRoom);

    void updateList(List<ClassRoom> list);

    void updateCourseForClass(int id);

    void setEnable(ClassRoom room, String checkBox);
}
