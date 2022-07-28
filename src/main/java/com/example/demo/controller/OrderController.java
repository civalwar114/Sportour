package com.example.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.OrderEntity;
import com.example.demo.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@RequestBody OrderEntity entity) {
		Random rand = new Random();
		try {
			String numStr1 = "";// 난수가 저장될 변수
			String numStr2 = "";// 난수가 저장될 변수
			String numStr3 = "";// 난수가 저장될 변수
			String numStr4 = "";// 난수가 저장될 변수
			for (int i = 0; i < 6; i++) {

				// 0~9 까지 난수 생성
				String ran1 = Integer.toString(rand.nextInt(10));
				String ran2 = Integer.toString(rand.nextInt(10));
				String ran3 = Integer.toString(rand.nextInt(10));
				String ran4 = Integer.toString(rand.nextInt(10));
				
				numStr1 += ran1;
				numStr2 += ran2;
				numStr3 += ran3;
				numStr4 += ran4;
				

			}
			entity.setOrdernumb(0L);
			entity.setHotelNum(numStr1);
			entity.setMatchNum(numStr2);
			entity.setPlaneNum(numStr3);
			entity.setPassNum(numStr4);
			List<OrderEntity> entities = service.create(entity);
			ResponseDTO<OrderEntity> response = ResponseDTO.<OrderEntity>builder().data(entities).build();
			return ResponseEntity.ok().body(response);

		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<OrderEntity> response = ResponseDTO.<OrderEntity>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping("/orderinfo")
	public ResponseEntity<?> outputOrder() {
		List<OrderEntity> entities = service.output();
		ResponseDTO<OrderEntity> response = ResponseDTO.<OrderEntity>builder().data(entities).build();
		return ResponseEntity.ok().body(response);
	}

}
