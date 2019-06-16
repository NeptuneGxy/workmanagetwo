package com.example.workmanagetwo.service;

import com.example.workmanagetwo.entity.Task;
import com.example.workmanagetwo.entity.TaskReply;
import com.example.workmanagetwo.repository.TaskReplyRepository;
import com.example.workmanagetwo.repository.TaskRepository;
import com.example.workmanagetwo.repository.TeacherRepository;
import com.example.workmanagetwo.entity.TaskReply;
import com.example.workmanagetwo.repository.TaskReplyRepository;
import com.example.workmanagetwo.repository.TaskRepository;
import com.example.workmanagetwo.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class TaskReplyService {
    @Autowired
    TaskReplyRepository replyRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public TaskReply getTaskReplyById(int id) {
        return replyRepository.findById(id).get();
    }

    public void reply(int taskId,int teacherId,String content) {

         TaskReply t=new TaskReply(content);
         t.setTask(taskRepository.getOne(taskId));
         t.setTeacher(teacherRepository.getOne(teacherId));
        LocalDateTime currentTime=LocalDateTime.now();
        if(currentTime.toInstant(ZoneOffset.of("+8")).toEpochMilli()>taskRepository.getOne(t.getTask().getId()).getDeadline().toInstant(ZoneOffset.of("+8")).toEpochMilli())
        {
            t.setReplyStatus(0);

        }else
        {
            t.setReplyStatus(1);

        }
        replyRepository.save(t);
        replyRepository.flush();

    }

    public List<Map<String,Object>> getAllTaskAndReply() {
        List<Map<String,Object>> list = replyRepository.getAllTaskAndReply();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Map<String,Object>> newList=new ArrayList<Map<String, Object>>();
        for(int i=0;i<list.size();i++){
            Map<String, Object> map = new HashMap<>();
            map.put("status", list.get(i).get("status"));
            LocalDateTime localDateTime = (LocalDateTime)  list.get(i).get("time");
            map.put("time", localDateTime.format(dateTimeFormatter));
            map.put("tcontent", list.get(i).get("tcontent"));
            map.put("rcontent",  list.get(i).get("rcontent"));
            map.put("name",  list.get(i).get("name"));
            map.put("title",list.get(i).get("title"));
            newList.add(map);
        }
        return newList;

    }
}
