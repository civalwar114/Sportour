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
	
	private String card;  //ī�� ��ȣ 16�ڸ�
	private String exp;  //ī�� �Ⱓ
	private String cvc;  //cvc
	private String passport;  //����
	private String engname;  //�����̸�
	private String peoNum;  //�ο���
	
	
	
	
	
	
}
