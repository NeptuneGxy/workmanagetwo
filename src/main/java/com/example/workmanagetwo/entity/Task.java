package com.example.workmanagetwo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.JsonPath;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String taskTitle;
    @Column(columnDefinition = "TEXT")
    private String taskContent;
    //任务状态，1表示尚未结束即进行中，0表示已结束，不能再回复
    private int taskStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadline;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;

    public Task(String taskTitle, int taskStatus, String taskContent, LocalDateTime deadline) {
        this.deadline = deadline;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
        this.taskTitle = taskTitle;
    }
}
