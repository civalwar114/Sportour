package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "Package")
@SequenceGenerator(
        name="PACKAGE_SEQUENCE_GENERATOR", // �젣�꼫�젅�씠�꽣紐�
        sequenceName="PACKAGE_SEQUENCE", // �떆���뒪紐�
        initialValue= 1, // �떆�옉 媛�
        allocationSize= 1 // �븷�떦�븷 踰붿쐞 �궗�씠利�
)
public class PackageEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PACKAGE_SEQUENCE_GENERATOR")
	@Column(name = "package_id")
	private int pno;
	private String title;
	private int price;
	private String content;
	private Date depDate;
	private int period;
	private Date matchDate;
//	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
//	@JoinColumn(name ="order_id")
//	private List<OrderEntity> orderList = new ArrayList<>();;
}
