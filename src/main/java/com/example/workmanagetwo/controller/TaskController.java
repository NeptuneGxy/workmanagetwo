package com.example.workmanagetwo.controller;

import com.example.workmanagetwo.entity.Task;
import com.example.workmanagetwo.entity.TaskReply;
import com.example.workmanagetwo.service.TaskReplyService;
import com.example.workmanagetwo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskReplyService taskReplyService;
    @PostMapping("/task/addTask")
    public Map addTask(@RequestBody Task task){

        return Map.of("tasks",taskService.addTask(task));
    }

    @PostMapping("/task/offTask")
    public Map offTask(@RequestBody Task task){
        return Map.of("tasks",taskService.offTask(task.getId()));
    }

    @PostMapping("/task/updateTask")
    public Map updateTask(@RequestBody Task task){
        return Map.of("tasks",taskService.updateTask(task));
    }

    @GetMapping("/task/taskList")
    public Map getTaskList(){
        return Map.of("tasks",taskService.getTaskList());
    }

    @PostMapping("/task/reply/{taskId}/{teacherId}/{content}")
    public Map reply(@PathVariable int taskId,@PathVariable int teacherId, @PathVariable String content)
    {
        taskReplyService.reply(taskId,teacherId,content);
        return Map.of("tasks",taskService.getTaskList());

    }

    @GetMapping("/task/replyList")
    public Map getReplyList(){
        return  Map.of("replys",taskReplyService.getAllTaskAndReply());
    }

}
