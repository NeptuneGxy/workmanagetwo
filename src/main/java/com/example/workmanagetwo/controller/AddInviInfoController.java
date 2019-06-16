package com.example.workmanagetwo.controller;

import com.example.workmanagetwo.entity.Inviinfo;
import com.example.workmanagetwo.service.AddInviinfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AddInviInfoController {
    @Autowired
    private AddInviinfoService addInviinfoService;
    @GetMapping("/getAll")
    public Map getInviinfos(){
        return Map.of("inviinfos", addInviinfoService.getAll());
    }
    @PostMapping("/addInviinfo")
    public Map addInviinfo(@RequestBody Inviinfo inviinfo){
        Inviinfo inviinfo1=addInviinfoService.addInviinfo(inviinfo);
        if(inviinfo1!=null)
            return Map.of("inviinfos", addInviinfoService.getAll());
        else
            return Map.of("message", "时间地点有冲突，请另行安排考场");
    }

    @PatchMapping("/updateStatus/{id}")
    public Map updateStatus(@PathVariable int id){
        return Map.of("updateinviinfo", addInviinfoService.updateStatus(id));
    }
}
