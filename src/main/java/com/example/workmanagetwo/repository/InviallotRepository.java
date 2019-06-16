package com.example.workmanagetwo.repository;

import com.example.workmanagetwo.entity.Inviallot;
import com.example.workmanagetwo.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface InviallotRepository extends CustomizedRepoistory<Inviallot, Integer>{
    @Query("SELECT a FROM Inviallot a WHERE a.inviinfo.id=:iid AND a.teacher.id=:tid")
    Inviallot findByIds(@Param("iid") int iid, @Param("tid") int tid);
    @Query("SELECT a FROM Inviallot a WHERE a.id=:id")
    Inviallot findById(@Param("id") int id);
    @Query("SELECT a FROM Inviallot a ")
    List<Inviallot> getInviallots();
    @Query("SELECT a FROM Inviallot a WHERE a.teacher.id=:tid AND (:startTime between a.inviinfo.startTime AND  a.inviinfo.endTime OR :endTime between a.inviinfo.startTime AND a.inviinfo.endTime OR a.inviinfo.startTime between :startTime AND  :endTime OR a.inviinfo.endTime between :startTime AND  :endTime)")
    List<Inviallot> findByTime(@Param("tid") int tid, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    @Query("SELECT t.id AS id,t.name AS name FROM Teacher t,Inviallot a WHERE a.inviinfo.id=:iid AND t.id=a.teacher.id")
    List<Map<String,Object>> getNames(@Param("iid") int iid);
    @Query("SELECT DISTINCT a.inviinfo.id FROM Inviallot a")
    List<Integer> getIds();


    //根据课程名查询allot
    @Query("SELECT a FROM Inviallot a WHERE a.inviinfo.cname=:cname")
    List<Inviallot> list(@Param("cname") String cname);

    //根据信息表id查询allot中教师
    @Query("SELECT a.teacher FROM Inviallot a WHERE a.inviinfo.id=:id")
    List<Teacher> findByInviinfo_id(@Param("id") int id);

    //根据课程名删除allot表
    @Modifying
    @Query("DELETE FROM Inviallot a WHERE a.inviinfo.cname=:cname")
    void deleteByInviinfo_Cname(@Param("cname") String cname);
    //根据分配表id名删除allot表
    @Modifying
    @Query("DELETE FROM Inviallot a WHERE a.inviinfo.id=:id")
    void deleteByInviinfo_id(@Param("id") int id);
}
