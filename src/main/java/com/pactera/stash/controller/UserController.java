package com.pactera.stash.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.pactera.stash.mapper.service.RecordService;
import com.pactera.stash.model.Record; 
  
//@EnableAutoConfiguration  
@RestController  
@RequestMapping("/")  
public class UserController { 
	@Autowired
	private RecordService recordService;
    @RequestMapping("/")  
    public String view() {
		Record record = new Record();
		record.setCode("1");
		record.setIndex(1.2111111111111);
		record.setTime(new Date());
		recordService.insert(record);
        return "Hello world!";  
    }  
  
} 
