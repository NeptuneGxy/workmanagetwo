package com.example.workmanagetwo.service;

import com.example.workmanagetwo.entity.Task;
import com.example.workmanagetwo.entity.TaskReply;
import com.example.workmanagetwo.repository.TaskRepository;
import com.example.workmanagetwo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> addTask(Task task) {
        taskRepository.save(task);
        taskRepository.flush();
        List<Task> list=taskRepository.findAll();
        return list;
    }



    public List<Task> offTask(int id) {

        Task task1=taskRepository.findById(id).get();
        task1.setTaskStatus(0);
        taskRepository.save(task1);
        taskRepository.flush();
        List<Task> list=taskRepository.findAll();
        return list;
    }

    public List<Task> getTaskList() {

        List<Task> list=taskRepository.findAll();
        return list;
    }

    public Task getTaskById(int id)
    {
        Task task=taskRepository.getOne(id);
       return task;
    }

    public List<Task> updateTask(Task task) {
        Task task1 = taskRepository.save(task);
        taskRepository.flush();
        List<Task> list=taskRepository.findAll();
        return list;
    }
}
