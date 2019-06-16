package com.example.workmanagetwo.service;

import com.example.workmanagetwo.entity.Teacher;
import com.example.workmanagetwo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    public List<Teacher> getTeacher(){
        return teacherRepository.getTeacher();
    }
    public Teacher findById(int id){
        return teacherRepository.findById(id);
    }
    public Teacher addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return teacherRepository.refresh(teacher);
    }

    public Teacher updateCount(int id){
        Teacher teacher=teacherRepository.findById(id);
        int count=teacher.getCount();
        teacher.setCount(count+1);
        teacherRepository.save(teacher);
        return teacherRepository.findById(id);
    }

    //获取所有教师
    public List<Teacher> getAll(){
        return teacherRepository.getAll();
    }
    //按id删除教师信息
    public List<Teacher> deleteById(int id){
        teacherRepository.deleteById(id);
        teacherRepository.flush();
        return teacherRepository.getAll();
    }
    //按id更新教师信息
    public List<Teacher> updateTeacherById(Teacher teacher){
        Teacher t = teacherRepository.getOne(teacher.getId());
        t.setName(teacher.getName());
        t.setBrief(teacher.getBrief());
        t.setTitle(teacher.getTitle());
        t.setPhone(teacher.getPhone());
        teacherRepository.save(t);
        teacherRepository.flush();
        return teacherRepository.getAll();
    }
}
