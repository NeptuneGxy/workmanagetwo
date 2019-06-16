package com.example.workmanagetwo.controller;

import com.example.workmanagetwo.entity.Inviallot;
import com.example.workmanagetwo.entity.Inviinfo;
import com.example.workmanagetwo.entity.Teacher;
import com.example.workmanagetwo.service.AddInviinfoService;
import com.example.workmanagetwo.service.InviallotService;
import com.example.workmanagetwo.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class InviallotController {
    @Autowired
    private InviallotService inviallotService;
    @Autowired
    private AddInviinfoService addInviinfoService;
    @Autowired
    private TeacherService teacherService;
    /**
     * 添加分配记录同时要修改监考信息中的状态字段为已分配
     * @param
     * @return
     */
    @PostMapping("/addInviallot/{iid}/{tid}")
    public Map addInviallot(@PathVariable int iid,@PathVariable String tid){
        String[] tids1=tid.split(",");
        Integer[] tids=new Integer[tids1.length];
        for(int i=0;i<tids1.length;i++){
            tids[i]=Integer.valueOf(tids1[i]);
        }
        for(int i=0 ;i<tids.length; i++){
            Inviinfo inviinfo=addInviinfoService.findById(iid);
            List<Inviallot> inviallot1= inviallotService.findByTime(tids[i],inviinfo.getStartTime(),inviinfo.getEndTime());
            Teacher teacher=teacherService.findById(tids[i]);
            if(inviallot1.size()!=0) {
                System.out.println("教师"+teacher.getName()+"在该时间段内有安排");
            }
            Inviallot inviallot = new Inviallot();
            inviallot.setInviinfo(inviinfo);
            inviallot.setTeacher(teacher);
            Inviallot inviallot2=inviallotService.addInviallot(inviallot);
            if(inviallot2!=null)
                System.out.println("此堂监考已经分配教师"+teacher.getName());

        }
        return Map.of("inviinfos", addInviinfoService.getAll());
    }
    @GetMapping("/getInviallots")
    public Map getInviallots(){
        return Map.of("inviallots", inviallotService.getAll());
    }

    @PostMapping("/updateAllot/{infoid}/{tids}")
    public Map updateAllot(@PathVariable int infoid,@PathVariable String tids){

        String[] tids1=tids.split(",");
        Integer[] tid=new Integer[tids1.length];
        for(int i=0;i<tids1.length;i++){
            tid[i]=Integer.valueOf(tids1[i]);
        }
        inviallotService.updateAllot(tid, infoid);
        return Map.of("inviallots", inviallotService.getAll());
    }
}
