package com.example.workmanagetwo.repository;

import com.example.workmanagetwo.entity.Inviinfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AddInviinfoRepository extends CustomizedRepoistory<Inviinfo, Integer>{
    @Query("SELECT i FROM Inviinfo i")
    List<Inviinfo> getAll();
    @Query("SELECT i FROM Inviinfo i WHERE i.id=:id")
    Inviinfo findById(@Param("id") int id);
    @Query("SELECT i FROM Inviinfo i WHERE i.address=:address AND (:startTime BETWEEN i.startTime AND  i.endTime OR :endTime BETWEEN i.startTime AND i.endTime OR i.startTime BETWEEN :startTime AND :endTime OR i.endTime BETWEEN :startTime AND :endTime)")
    List<Inviinfo> findByTime(@Param("address") String address, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
    @Query("SELECT i FROM Inviinfo i  WHERE i.endTime<NOW()")
    List<Inviinfo> dynamicUpdate();


}
