package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	
	//데이터를 프론트에서 받아 오고
	//자리수 일치만 확인하고
	//랜덤 수를 그냥 알아서 보내 주는 형식으로	
	//포스트 맵핑으로 받아오기?
	//아님 디티오에 카드 번호 등이 입력/저장 된것이 확인 되면
	//이때 랜덤 숫자를 뿌려주게
	
	//length로 특정 길이에 맞는지 확인 한 후 랜덤 숫자 뿌리기.
	
	// 보내줄 랜덤 코드들은 - 비행기-호텔-입장권-교통패스 4개
	@PostMapping
	public void payment() {
		//if 어쩌구 length = 16 && length  어쭤구 헤서 
		//비교 후 맞으면 랜덤 숫자 뿌리기
		
	}
	
	
}
