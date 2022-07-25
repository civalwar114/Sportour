package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmbEntity;

@Repository
public interface EmbRepository extends JpaRepository<EmbEntity, String> {

	List<EmbEntity> findBycountryContaining(String keyword);
	
} 
