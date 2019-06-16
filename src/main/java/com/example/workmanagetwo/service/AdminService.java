package com.example.workmanagetwo.service;

import com.example.workmanagetwo.entity.Admin;
import com.example.workmanagetwo.entity.Teacher;
import com.example.workmanagetwo.repository.AdminRepository;
import com.example.workmanagetwo.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    TeacherRepository teacherRepository;
    public void addAdmin(int id){
        Teacher t = teacherRepository.getOne(id);
        Admin admin = new Admin();
        admin.setName(t.getName());
        admin.setPassword("$2a$10$h8VI78CxLo5Jhydj0fbvj.LBT34y1Xwif102RaLWo/5.qMdt.Jwju");
        adminRepository.save(admin);
        adminRepository.flush();
    }
}
