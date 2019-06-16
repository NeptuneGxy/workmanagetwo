package com.example.workmanagetwo.service;

import com.example.workmanagetwo.entity.Inviinfo;
import com.example.workmanagetwo.repository.AddInviinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class AddInviinfoService {
    @Autowired
    private AddInviinfoRepository addInviinfoRepository;
    @Autowired
    @PersistenceContext
    private EntityManager em;
    /**
     * 获得所有的监考信息
     * @return
     */
    public List<Inviinfo> getAll(){
        return addInviinfoRepository.getAll();
    }

    /**
     * 添加监考信息,先查询表中是否存在时间相同地点相同的记录，有则不能添加，提示冲突，没有才可以添加
     * @param inviinfo
     * @return
     */
    public Inviinfo addInviinfo(Inviinfo inviinfo){
        List<Inviinfo> list=addInviinfoRepository.findByTime(inviinfo.getAddress(),inviinfo.getStartTime(),inviinfo.getEndTime());
        if(list.size()!=0){
            System.out.println("list的大小："+list.size());
            System.out.println("时间地点有冲突，请另行安排考场");
            return null;
        }
        addInviinfoRepository.save(inviinfo);
        addInviinfoRepository.flush();
        return inviinfo;
    }
    public Inviinfo findById(int id){
        em.refresh(addInviinfoRepository.findById(id));
        return addInviinfoRepository.findById(id);
    }
    /**
     * 修改监考信息的状态
     */
    public Inviinfo updateStatus(int id){
        Inviinfo inviinfo=addInviinfoRepository.findById(id);
        inviinfo.setStatus("已分配");
        addInviinfoRepository.save(inviinfo);
        /*addInviinfoRepository.flush();
        return addInviinfoRepository.refresh(inviinfo);*/
        return addInviinfoRepository.findById(id);
    }
//    @Scheduled(cron = "*/60 * * * * *")
//    public List<Inviinfo> dynamicUpdate(){
//        List<Inviinfo> list= addInviinfoRepository.dynamicUpdate();
//        list.forEach(inviinfo -> {
//            inviinfo.setStatus("已完成");
//            addInviinfoRepository.save(inviinfo);
//        });
//        return list;
//    }
}
