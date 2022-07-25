package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmbSearchDTO {
	
	
	
	//private String center_tel_no;
	//private String country_eng_nm;
	private String country_iso_alp2;  //iso 코드
	private String country_nm;   // 나라 이름
	//private String embassy_cd;
	private String embassy_kor_nm; //대사관 이름
	//private String embassy_lat;  //위도
	//private String embassy_lng;  //경도
	//private String embassy_manage_ty_cd;
	//private String embassy_manage_ty_cd_nm;
	//private String embassy_ty_cd ;
	//private String embassy_ty_cd_nm ;
	private String emblgbd_addr;  //주소
	//private String free_tel_no ;
	private String tel_no;  //전화
	private String urgency_tel_no;  //전화2
	
	private String serachdata;
	
}
