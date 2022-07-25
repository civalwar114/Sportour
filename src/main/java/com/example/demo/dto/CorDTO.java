package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorDTO {

	private String resultCode; //결과 코드
	private String resultMsg; //결과메세지
	private String numOfRows; //한 페이지 결과수 
	private String pageNo; //페이지 번호
	private String totalCount; //전체 결과수
	private String currentCount; //현재 결과수
	private String country_nm; //나라명
	private String country_eng_nm;  //영문명
	private String country_iso_alp2; //국가 코드
	private String notice_id; //조치현황 id
	private String title;  // 해외 입국자에 대한 조치
	private String txt_origin_cn; //출발 지역 별 입국제한
	private String html_origin_cn; //크게 쓸모는 없어 보임
	
}
