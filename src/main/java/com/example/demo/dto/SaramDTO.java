package com.example.demo.dto;

import com.example.demo.model.SaramEntity;
import com.example.demo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaramDTO {
	private String No;
	private String id;
	private String email;
	private String phone;
	
	
	   public SaramDTO(final SaramEntity entity) {
		      this.id = entity.getId();
		      this.No = entity.getNo();
		      this.email=entity.getEmail();
		      this.phone=entity.getPhone();
		   }
	   
	   
	   public static SaramEntity saEntity(final SaramDTO dto) {
		      return SaramEntity.builder()
		            .id(dto.getId())
		            .No(dto.getNo())
		            .email(dto.getEmail())
		            .phone(dto.getPhone())
		            .build();
		   }
	   
	   
	   
	
}
