package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

	@Id
	private int ordernumb;
	
	private String card;  //카드 번호 16자리
	private String exp;  //카드 기간
	private String cvc;  //cvc
	private String passport;  //여권
	private String engname;  //영문이름
	private String peoNum;  //인원수
	
	
	
	
	
	
}
