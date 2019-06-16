package com.example.workmanagetwo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int count;   //监考次数
    private String title;   //职称
    //简介内容，普通长度可能不够
    @Column(columnDefinition = "TEXT")
    private String brief;    //教师简介
    private String phone;
    @OneToMany(mappedBy = "teacher")
    private Set<Inviallot> inviallots;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
}
