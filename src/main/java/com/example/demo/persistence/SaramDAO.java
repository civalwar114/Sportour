package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.example.demo.model.SaramEntity;


@Repository
public interface SaramDAO extends JpaRepository<SaramEntity, String> {
	
//	List<SaramEntity> findByNo(String id);
	
}
