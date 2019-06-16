package com.example.workmanagetwo.repository;


import com.example.workmanagetwo.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface TaskRepository extends CustomizedRepoistory<Task, Integer> {


    @Query("SELECT t FROM Task t WHERE t.id=:tid")
    List<Task> list(@Param("tid") int tid);


}
