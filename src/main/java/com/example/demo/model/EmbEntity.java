package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="embtest")
public class EmbEntity {

	@Id
	private int tempno;
	private String country_iso_alp2;  //iso 코드
	private String country;   // 나라 이름
	
	private String embassy_kor_nm; //대사관 이름

	private String emblgbd_addr;  //주소

	private String tel_no;  //전화
	private String urgency_tel_no;  //전화2
	
}
