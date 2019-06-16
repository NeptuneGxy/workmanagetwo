package com.example.workmanagetwo.repository;

import com.example.workmanagetwo.entity.Inviinfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InviinfoRepository extends CustomizedRepoistory<Inviinfo, Integer> {
    @Query("SELECT info FROM Inviinfo info")
    List<Inviinfo> list();

    //Inviinfo updateInviinfo(int id,String status);

    @Query("SELECT info.startTime FROM Inviinfo info")
    List<LocalDateTime> getTimes();

    @Query("SELECT info FROM Inviinfo info WHERE info.cname=:cname")
    Inviinfo find(@Param("cname") String cname);
}
