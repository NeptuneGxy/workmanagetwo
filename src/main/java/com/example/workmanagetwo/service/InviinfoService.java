package com.example.workmanagetwo.service;

import com.example.workmanagetwo.entity.Inviinfo;
import com.example.workmanagetwo.repository.InviinfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class InviinfoService {
    @Autowired
    InviinfoRepository inviinfoRepository;
    //修改信息表中监控分配状态
    public Inviinfo updateInviinfo(int id,String status){
        Inviinfo i=inviinfoRepository.findById(id).get();
        i.setStatus(status);
        return inviinfoRepository.save(i);
    }
    //查看监考时间
    public List<LocalDateTime> getTimes(){
        List<LocalDateTime> list=inviinfoRepository.getTimes();
        return list;
    }
    //定时查看监考信息
    //@Scheduled(cron="0 0 7,19 * * *")
    @Scheduled(cron="0 0 7,19 * * *")
    public void findInfo(){
        LocalDateTime current=LocalDateTime.now();
        List<LocalDateTime> starts=getTimes();
        for(LocalDateTime t:starts){
            log.debug("开始时间stats:"+t);
            Duration duration=Duration.between(current, t);
            if(duration.toHours()<=24){
                log.debug("间隔小时："+duration.toHours());
                log.debug("考试即将开始，考试时间："+t);
            }
        }
    }
}
