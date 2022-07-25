package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.SaramDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.SaramEntity;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.SaramService;
import com.example.demo.service.TodoService;

import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping("saram")
@Slf4j
public class SaramController {
	@Autowired
	private SaramService service;
	
	@Autowired
	private TodoService service2;
	
	@GetMapping("/getMapping")
	public String getMapping() {
		
		return "Get - getMapping...";
	}
	
	  @GetMapping("/test")
	   public ResponseEntity<?> responseEntity() {
	      List<String> list = new ArrayList<String>();
	      list.add(service.testService());
	      ResponseDTO<String> body = ResponseDTO.<String>builder().data(list).build();
	      return ResponseEntity.ok().body(body);
	   }
	   
	  @GetMapping("/list")
	  public List<TodoDTO> list(){
		  List<TodoDTO> list = new ArrayList<TodoDTO>();
		  List<TodoEntity> listAll = service2.findAll();
		  	for(TodoEntity entity:listAll) {
		  		list.add(new TodoDTO(entity));
		  	}
		  return list;
		  
	  }
	
	  
	  @PostMapping("/insertt")
	   public ResponseEntity<TodoDTO> insert(TodoDTO dto) {
	      log.info(">>>>>> ", dto.toString());
	      System.out.println(">>>>>> " + dto.toString());
	      return ResponseEntity.<TodoDTO>ok().body(dto);
	   }
 
	@PostMapping("/inserttt")
	public String inserttt(TodoDTO dto) {
		
		log.info(">>>>",dto.toString());
		return dto.toString();
	}
	
	
	
	

	  @GetMapping("/list2")
	  public List<SaramDTO> list2(){
		  List<SaramDTO> list = new ArrayList<SaramDTO>();
		  List<SaramEntity> listAll = service.findAll();
		  	for(SaramEntity entity:listAll) {
		  		list.add(new SaramDTO(entity));
		  	}
		  return list;	  
	  }
	
	
	
	
	@PostMapping("/insert")
	public String insert(SaramDTO dto) {
		
		log.info(">>>>",dto.toString());
		return dto.toString();

	}
	
}
