package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrderEntity;
import com.example.demo.model.PackageEntity;
import com.example.demo.persistence.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<OrderEntity> output() {
		return repository.findAll();
	}
	
	public List<OrderEntity> create(final OrderEntity entity) {

		repository.save(entity); 
		log.info("Entity Id : {} is saved.");
		return output();
	}
		
	
	
	
	
	
	
}
