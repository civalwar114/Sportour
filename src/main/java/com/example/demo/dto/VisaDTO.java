package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaDTO {
	
	private String resultCode;
	private String resultMsg;
	private String numOfRows;
	private String pageNo;
	private String totalCount;
	private String currentCount;
	private String country_nm; //국가명
	private String have_yn; //여권 소지 여부
	private String gnrl_pspt_visa_yn; //일반 여권 입국 허가 요건 여부
	private String gnrl_pspt_visa_cn; //일반 여권 입국 허가 요건 내용

	private String remark;
}
