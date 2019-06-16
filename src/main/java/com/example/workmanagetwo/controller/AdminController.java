package com.example.workmanagetwo.controller;

import com.example.workmanagetwo.service.AdminService;
import com.example.workmanagetwo.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;
    @PostMapping("/addAdmin/{id}")
    public Map addAdmin(@PathVariable int id){
        adminService.addAdmin(id);
        return Map.of("teachers", teacherService.getTeacher());
    }
}
