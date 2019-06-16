package com.example.workmanagetwo.repository;

import com.example.workmanagetwo.entity.TaskReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskReplyRepository extends CustomizedRepoistory<TaskReply, Integer> {

    @Query("select t.teacher.name as name,t.task.taskTitle as title,t.task.taskContent as tcontent,t.replyContent as rcontent,rc.name as status,t.replyTime as time from TaskReply t,ReplyCode rc where t.replyStatus=rc.status")
    public List<Map<String,Object>> getAllTaskAndReply();
}
