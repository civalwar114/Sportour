package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {


}
