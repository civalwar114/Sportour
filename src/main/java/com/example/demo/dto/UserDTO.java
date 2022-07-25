package com.example.demo.dto;


import com.example.demo.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String token;
	private String email;
	private String username;
	private String password;
	private String id;
	private String engname;
	private String korname;
	private String phone;
	

	
	 public static UserEntity usEntity(final UserDTO dto) {
	      return UserEntity.builder()	         
	            .email(dto.getEmail())
	            .username(dto.getUsername())
	            .password(dto.getPassword())
	            .engname(dto.getEngname())
	            .korname(dto.getKorname())
	            .phone(dto.getPhone())
	            .build();
	   }
	
	
}
