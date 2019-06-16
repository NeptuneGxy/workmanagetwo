package com.example.workmanagetwo.service;

import com.example.workmanagetwo.entity.Inviallot;
import com.example.workmanagetwo.entity.Inviinfo;
import com.example.workmanagetwo.entity.Teacher;
import com.example.workmanagetwo.repository.InviallotRepository;
import com.example.workmanagetwo.repository.InviinfoRepository;
import com.example.workmanagetwo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InviallotService {
    @Autowired
    private InviallotRepository inviallotRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AddInviinfoService addInviinfoService;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    InviinfoRepository inviinfoRepository;
    public Inviallot findById(int id){
        return inviallotRepository.findById(id);
    }
    public Inviallot findByIds(int iid,int tid){
        return inviallotRepository.findByIds(iid, tid);
    }

    /**
     * 添加分配信息前查询是否存在所分配的老师在将分配的监考期间会同时监考两次，如果存在，提示有冲突，但仍然可以分配
     * @param inviallot
     * @return
     */
    public Inviallot addInviallot(Inviallot inviallot){
        Inviallot inviallot1=inviallotRepository.findByIds(inviallot.getInviinfo().getId(), inviallot.getTeacher().getId());
        if(inviallot1==null) {
            inviallotRepository.save(inviallot);
            addInviinfoService.updateStatus(inviallot.getInviinfo().getId());
            teacherService.updateCount(inviallot.getTeacher().getId());
            return inviallotRepository.refresh(inviallot);
        }else{
            return null;
        }
    }
    public List<Inviallot> getInviallots(){
        return inviallotRepository.getInviallots();
    }
    public List<Inviallot> findByTime(int tid, LocalDateTime startTime,LocalDateTime endTime){
         return inviallotRepository.findByTime(tid,startTime,endTime);
    }
    public List<Map<String,Object>> getAll(){
        List<Map<String,Object>> inviallots=new ArrayList<Map<String,Object>>();
        List<Integer> ids=inviallotRepository.getIds();
        for(int i=0;i<ids.size();i++){
            List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
            Map<String,Object> map1=new HashMap<String,Object>();
            map1.put("id", Integer.valueOf(ids.get(i)));

            List<Map<String,Object>> teachers=inviallotRepository.getNames(ids.get(i));
            Inviinfo inviinfo=addInviinfoService.findById(ids.get(i));
            String cname=inviinfo.getCname();
            map1.put("cname", cname);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("teachers", teachers);
            map.put("inviinfo", map1);
            inviallots.add(map);
        }
        return inviallots;
    }

    public void updateAllot(Integer[] tid, int infoid){
        //根据分配表中信息表id查找教师id
        List<Teacher> teachers=inviallotRepository.findByInviinfo_id(infoid);
        //修改教师的监考次数（-1）
        for(Teacher tc:teachers){
            teacherRepository.UpdateCount(tc.getCount()-1, tc.getId());
        }
        //根据信息表id删除分配表中的数据
        inviallotRepository.deleteByInviinfo_id(infoid);
        //新增分配表，传入参数为课程名，教师id
        Map<String,Inviallot> map=new HashMap<String, Inviallot>();
        Inviinfo info=inviinfoRepository.findById(infoid).get();

        for(int i=0;i<tid.length;i++){
            Inviallot inviallot=new Inviallot();
            //有错加get()
            Teacher t=teacherRepository.findById(tid[i]).get();
            //修改教师监考次数（次数+1）
            teacherRepository.UpdateCount(t.getCount()+1, t.getId());
            inviallot.setTeacher(t);
            inviallot.setInviinfo(info);
            Inviallot allot=inviallotRepository.saveAndFlush(inviallot);
        }
    }
}
