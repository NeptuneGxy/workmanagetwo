package com.example.workmanagetwo.repository;

import com.example.workmanagetwo.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CustomizedRepoistory<Teacher, Integer>{
    @Query("SELECT t FROM Teacher t WHERE t.id=:id")
    Teacher findById(@Param("id") int id);
    @Query("SELECT t FROM Teacher t ORDER BY t.count ASC")
    List<Teacher> getTeacher();

    //修改教师监考次数
    @Modifying
    @Query("UPDATE Teacher t SET t.count=:count WHERE t.id=:id")
    void UpdateCount(@Param("count") int count,@Param("id") int id);

    //获取所有教师信息
    @Query("SELECT t from Teacher t")
    List<Teacher> getAll();
}
