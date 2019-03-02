package com.msp.givn.service.classroom.impl;

import com.msp.givn.entity.ClassRoom;
import com.msp.givn.repository.classroom.ClassRoomRepository;
import com.msp.givn.service.classroom.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Override
    public ClassRoom findById(int id) {
        return classRoomRepository.findById(id);
    }

    @Override
    public ClassRoom findByName(String name) {
        return classRoomRepository.findByName(name);
    }

    @Override
    public List<ClassRoom> findByTeacherId(int id) {
        return classRoomRepository.findByTeacherId(id);
    }

    @Override
    public List<ClassRoom> findAll() {
        return classRoomRepository.findAll();
    }

    @Override
    public List<ClassRoom> findByCourseId(int id) {
        return classRoomRepository.findByCourseId(id);
    }

    @Override
    public void deleteById(int id) {
        classRoomRepository.deleteById(id);
    }

    @Override
    public ClassRoom save(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @Override
    public void updateList(List<ClassRoom> list) {
        classRoomRepository.updateList(list);
    }

    @Override
    public void updateCourseForClass(int id) {
        List<ClassRoom> roomList = findByCourseId(id);
        if (roomList != null) {
            for (ClassRoom room : roomList) {
                room.setCourseId(1);
            }
            updateList(roomList);
        }
    }

    @Override
    public void setEnable(ClassRoom room, String checkBox) {
        if (checkBox != null) {
            room.setEnabled(true);
        } else {
            room.setEnabled(false);
        }
    }
}
