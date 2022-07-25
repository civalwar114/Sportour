package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmbDTO;
import com.example.demo.dto.SaramDTO;
import com.example.demo.model.EmbEntity;
import com.example.demo.persistence.EmbRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmbService {
	
	@Autowired
	EmbRepository repository;
	
	public List<EmbDTO> list(){
		List<EmbDTO> dtoList = new ArrayList<EmbDTO>();		
		return dtoList;
	}
	 
	
	public List<EmbDTO> list2(){
		List<EmbEntity> list = repository.findAll();
		List<EmbDTO> dtoList = new ArrayList<EmbDTO>();
		for(EmbEntity entity:list) {
			dtoList.add(new EmbDTO(entity));
		}
		return dtoList;
	}
	
	public List<EmbEntity> list3(){
		List<EmbEntity> dtoList = new ArrayList<EmbEntity>();		
		return dtoList;
	}
	
	
	
	
	@Transactional
	public List<EmbEntity> search(String keyword){
		List<EmbEntity> embs=repository.findBycountryContaining(keyword);
		return embs;
		
	}
	

	
}
