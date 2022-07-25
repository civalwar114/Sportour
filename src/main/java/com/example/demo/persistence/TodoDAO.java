package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoEntity;

@Repository
public interface TodoDAO extends JpaRepository<TodoEntity, String> {
	List<TodoEntity> findByuserId(String id);
}
