package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlrtDTO {

	private String country_nm; //
	
	private String title; //
	private String txt_origin_cn; //
	private String wrt_dt; //
	
}
