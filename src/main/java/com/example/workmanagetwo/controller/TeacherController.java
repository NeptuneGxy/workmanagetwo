package com.example.workmanagetwo.controller;

import com.example.workmanagetwo.entity.Teacher;
import com.example.workmanagetwo.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class TeacherController {
    @Autowired
    private TeacherService  teacherService;
    @PostMapping("/addTeacher")
    public Map addTeacher(@RequestBody Teacher teacher){
        teacherService.addTeacher(teacher);
        return Map.of("teachers", teacherService.getAll());
    }
    @GetMapping("/getTeacherByNum")
    public Map getTeacher(){
        return Map.of("teachers", teacherService.getTeacher());
    }

    @GetMapping("/getAllTeacher")
    public Map getAll(){
        return Map.of("teachers",teacherService.getAll());
    }

    @PostMapping("/updateTeacher")
    public Map updateTeacher(@RequestBody Teacher teacher){
        System.out.println("老师的id："+teacher.getId());
        return Map.of("teachers", teacherService.updateTeacherById(teacher));
    }
}
