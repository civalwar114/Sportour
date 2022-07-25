package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.security.TokenProvider;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
		//�뜝�룞�삕泥��뜝�룞�삕 �뜝�뙏�눦�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝占�
		try {
			UserEntity user = UserEntity.builder().email(userDTO.getEmail())
					.username(userDTO.getUsername()).password(userDTO.getPassword())
					.engname(userDTO.getEngname()).korname(userDTO.getKorname())
					.phone(userDTO.getPhone())
					.build();
			//�뜝�룞�삕�뜝�룦�뒪紐뚯삕 �뜝�떛�슱�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�뜲由у뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			UserEntity registeredUser = userService.create(user);
			UserDTO responseUserDTO = UserDTO.builder().email(registeredUser.getEmail())
					.username(registeredUser.getUsername()).id(registeredUser.getId()).password(userDTO.getPassword())
					.engname(userDTO.getEngname()).korname(userDTO.getKorname())
					.phone(userDTO.getPhone())
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		}catch(Exception e) {
			//�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�뙎�궪�삕 �뜝�떦�냲�삕 �뜝�떛誘�琉꾩삕 �뜝�룞�삕�뜝�룞�삕�듃�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떦�뙋�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕DTO�뜝�룞�삕 �뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�떗怨ㅼ삕 �뜝�뙎�냲�삕 �뜝�룞�삕�뜝�룞�삕DTO�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity
					.badRequest()
					.body(responseDTO);
		}		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword());
		
		if(user != null) {
			final String token = tokenProvider.create(user);
			final UserDTO respoUserDTO = UserDTO.builder()
					.email(user.getEmail()).id(user.getId()).token(token)
					.korname(user.getKorname()).engname(user.getEngname())
					.phone(user.getPhone()).build();
			return ResponseEntity.ok().body(respoUserDTO);		
		}else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("로그인 실패").build();
			return ResponseEntity.badRequest().body(responseDTO);			
		}
		
	}
		
	
	@PutMapping("/update")
	public UserEntity modify(@RequestBody UserEntity entity){
		
		UserEntity user = userService.modify(entity);
		
				
		return user;
	}
	
	
	/*
	@DeleteMapping("/delete")
	public void delete(@RequestBody UserDTO dto ){
		try {	
			  UserEntity entity = UserDTO.usEntity(dto);
			  System.out.println(entity);			  			  
			  userService.delete(entity);
			 		
		}catch(Exception e) {
			
		}
	}*/
	
	@DeleteMapping("/delete/{email}")
	public void delete2(@PathVariable String email){
		userService.delete2(email);
		//�뜝�룞�삕�뜝�룞�삕�듃�뜝�룞�삕�뜝�룞�삕 �듅�뜝�룞�삕 �뜝�룞�삕�듉�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�떛琉꾩삕�듃 �뜝�떎怨ㅼ삕 �뜝�떦紐뚯삕�붷뜝占�
	}

	
	
	
	
	
}
