package com.example.demo.model;

import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="order_id")
	private Long ordernumb;

	private String hotelNum; // 호텔
	private String planeNum; // 비행기
	private String passNum; // 교통패스
	private String matchNum; // 경기 입장권

	/*private String userId;// 
	private String card; // 카드번호
	private String exp; // 카드 기한
	private String cvc; // cvc
	private String passport; // 여권
	private String engname; // 영문이름 */
	private int peoNum; // 인원수
	@ManyToOne
	@JoinColumn(name = "package_id")
	private PackageEntity pack;

}
