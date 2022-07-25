package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints= {@UniqueConstraint(columnNames = "email")})
public class UserEntity {
	@Id
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;  //아이디
	
	@Column(nullable = false)
	private String username;  // 닉네임?
	
	@Column(nullable = false ,unique = true)
	private String email;  //이메일
	
	@Column(nullable = false)
	private String password;  //비밀번호
	
	@Column(nullable = false)
	private String korname; //한글 이름
	
	@Column(nullable = false)
	private String engname; //영문 이름
	
	@Column(nullable = false)
	private String phone; //전화번호
	 
	
}
