package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SaramEntity;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.SaramDAO;
import com.example.demo.persistence.TodoDAO;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Service

@Slf4j
public class SaramService {

	@Autowired 
	private SaramDAO repository;
	
	@Autowired 
	private TodoDAO repository2;
	
		
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("°¡³ª´Ù").build();		
		repository2.save(entity);		
		TodoEntity savedEntity = repository2.findById(entity.getId()).get();		
		return savedEntity.getTitle();
	}

	public List<SaramEntity>findAll(){
		
		
		return repository.findAll();
		
	}
	
}
