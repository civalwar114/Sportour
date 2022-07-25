package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private String card;  //카드 번호 16자리
	private String cardper;  //카드 기간
	private String cvc;  //cvc
	private String passport;  //여권
	private String engname;  //영문이름
	private String peoNum;  //인원수
	
	
	
}
