package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CorDTO;
import com.example.demo.dto.EmbDTO;

import com.example.demo.model.EmbEntity;

import com.example.demo.service.EmbService;


import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping("travinfo")
@Slf4j
public class ApiController {

	
	static List<String> embList1 = new ArrayList(); //나라명
	static List<String> embList2 = new ArrayList<String>(); //나라명
	static List<String> embList3 = new ArrayList(); // 대사관 위치
	static List<String> embList4 = new ArrayList(); //전화
	static List<String> embList5 = new ArrayList<String>(); //긴급전화
	static List<String> embList6 = new ArrayList(); //비우기용
	
	
	@Autowired
	EmbService service2;
	
	
	
	//@CrossOrigin(origins="http://localhost:3000/")
	@GetMapping("/embinfo")
	public ArrayList<EmbDTO> apitest(){	
		 List<EmbDTO> embtest = service2.list();
		 
		   try {
			  
		    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/EmbassyService2/getEmbassyList2"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=G3AQbacYcF%2BgJmIYRBbImXEt49jd8jLdzAYjZb%2FF%2Fe%2BhFeUeT3JS3qRIgwhmuhI16uhhqlXfFJ3Uf2K61USaQw%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
		        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
		        // urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/     
		        // urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode("GH", "UTF-8")); /*ISO 2자리코드*/
		        
		        Map<String, Object> Embmap = new HashMap<String, Object>();
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  //"UTF-8" 를 추가해주니까 한글로 받아왔다(06-23)
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
		        }
		        StringBuilder sb = new StringBuilder();
		        String line;
		        String result ="";
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		            result = result.concat(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString()); // 자바로 받아 오는건 확인됨 x, 아침에는 됬는데 그지같은게 갑자기 XML형식으로 받아옴 ㅗㅗㅗㅗ//0608 10시 다시 JSON으로 받아옴
		      
		        //json 파싱 연습
		        JSONParser parser = new JSONParser();
		        JSONObject obj = (JSONObject)parser.parse(result);
		        
		        JSONArray parse_listArr = (JSONArray)obj.get("data");
		       //초기화 시켜주기
		         embList1.clear();
		         embList2.clear();
		         embList3.clear();
		       //  embList4.clear();
		         embList5.clear();
		         embList6.clear();     
		         embtest.clear();
		        
		      //  System.out.println(parse_listArr.size());
		        for (int i=0;i< parse_listArr.size();i++) {
		        	 JSONObject Emb = (JSONObject) parse_listArr.get(i);
		        	 String c_tel_no = (String) Emb.get("center_tel_no"); // 몰?루 null값이 대부분  ty-cd가 40인 곳이 이 번호를 가짐, 호주도 가짐
		        	 String eng_nm  = (String) Emb.get("country_eng_nm"); //나라 영어명
		        	 String iso  = (String) Emb.get("country_iso_alp2"); // ISO코드
		        	 String co_nm  = (String) Emb.get("country_nm"); // 나라 이름 -
		        	 String em_cd  = (String) Emb.get("embassy_cd" ); // 코드 네임 같음
		        	 String em_k_nm  = (String) Emb.get("embassy_kor_nm"); // 대사관명 -
		        	 String em_lt  = String.valueOf( Emb.get("embassy_lat")); // 위도
		        	 String em_ln  = String.valueOf( Emb.get("embassy_lng")); // 경도
		        	 String em_mn_ty_cd  = (String) Emb.get("embassy_manage_ty_cd"); // 1 -일반 , 2-겸임, 3-관할,
		        	 String em_mn_ty_cd_nm  = (String) Emb.get("embassy_manage_ty_cd_nm"); //
		        	 String em_ty_cd  = (String) Emb.get("embassy_ty_cd"); // 10 -대사관, 20 -영사관,30-대표부,40- (국제기구 대표부..),50 -분관 ,60-(영사관겸 국제민간항공대표부),70-출장소(일반?),80- 대사관 본분관,90-(잘모르겠음),100-(잘모르겠음),110-(모르겠음),130- 출장소(대,영사관)
		        	 String em_ty_cd_nm  = (String) Emb.get("embassy_ty_cd_nm"); //
		        	 String em_addr  = (String) Emb.get("emblgbd_addr");   //대사관 주소 -
		        	 String free_tel  = (String) Emb.get("free_tel_no");  //모름 일단 null값이 대부분 ty-cd가 40인 곳이 이 번호를 가짐,호주도 가짐
		        	 String tel_no  = (String) Emb.get("tel_no"); //대사관 전화 번호 -
		        	 String ur_tel  = (String) Emb.get("urgency_tel_no");  //긴급 전화 -
		        	
		        	 StringBuffer sb2 = new StringBuffer();
		        	// sb2.append("나라 이름 : "+ eng_nm);
		        	// System.out.println(sb2.toString()); 
		        	 embtest.add(new EmbDTO(iso, co_nm, em_k_nm,  em_addr,  tel_no, ur_tel));
		        	 embList1.add(iso);
		        	 embList2.add(co_nm);  // 한글이 깨짐 -> (06-22 해결됬음)  
		        	// embtest2.add(new EmbEntity(i, iso, co_nm, em_k_nm,  em_addr,  tel_no, ur_tel));
		        }
	        
		      }catch (Exception e) {
		          e.printStackTrace();
		      }
	 
		return (ArrayList<EmbDTO>) embtest;
	}
	

	@GetMapping("/searchCo12")
	public List<EmbEntity> search(String keyword) {
		List<EmbEntity> searchList = service2.search(keyword);
		
		//keyword="가나";
		System.out.println(keyword);
		
		return searchList;
	}
	
	@GetMapping("/searchCo")
	public void testtest(@RequestParam String cont) {
		System.out.println(cont);
		

		//이것을 스프링 부트가 아닌 직접 처리 하면 될듯
		// filter사용
		//먼저 리액트에서 리스트에 담고
		//이것을 리스트.js 로 넘기고 담아두고 
		//다시 보여주는것 부터 구현 한 다음 해보면 될듯
		
		//검색기능 리액트로 거의다 됬다
		//다만 뭔가 덜 작동됨
		//usestate를 잘 쓰면 될거 같은데
		//useRef라는것도 존재함
		
		
	}
	
	
	
	static ArrayList<CorDTO> worldcor = new ArrayList<CorDTO>();
	
	//@CrossOrigin(origins="http://localhost:3000/") //리액트 연결했을때 코스 오리진 오류 해결
	@GetMapping("/worcorinfo")
	public ArrayList<CorDTO> apitest2(){
		
		try {
	    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryOverseasArrivalsService/getCountryOverseasArrivalsList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=G3AQbacYcF%2BgJmIYRBbImXEt49jd8jLdzAYjZb%2FF%2Fe%2BhFeUeT3JS3qRIgwhmuhI16uhhqlXfFJ3Uf2K61USaQw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
	     
	        
	        Map<String, Object> Embmap = new HashMap<String, Object>();
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  //"UTF-8" 를 추가해주니까 한글로 받아왔다(06-23)
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        String result ="";
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	            result = result.concat(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString()); // 자바로 받아 오는건 확인됨 x, 아침에는 됬는데 그지같은게 갑자기 XML형식으로 받아옴 ㅗㅗㅗㅗ//0608 10시 다시 JSON으로 받아옴
	      
	        //json 파싱 연습
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(result);
	        
	        JSONArray parse_listArr = (JSONArray)obj.get("data");
	       //초기화 시켜주기
	    
	        worldcor.clear();
	        
	      //  System.out.println(parse_listArr.size());
	        for (int i=0;i< parse_listArr.size();i++) {
	        	 JSONObject worldcorona = (JSONObject) parse_listArr.get(i);
	        	 String resultCode = (String) worldcorona.get("resultCode"); // 
	        	 String resultMsg  = (String) worldcorona.get("resultMsg"); //
	        	 String numOfRows  = (String) worldcorona.get("numOfRows"); // 
	        	 String pageNo  = (String) worldcorona.get("pageNo"); // -
	        	 String totalCount  = (String) worldcorona.get("totalCount" ); // 
	        	 String currentCount  = (String) worldcorona.get("currentCount"); // ----
	        	 String country_nm  = (String) worldcorona.get("country_nm"); // -----
	        	 String country_eng_nm  = (String) worldcorona.get("country_eng_nm"); //
	        	 String country_iso_alp2  = (String) worldcorona.get("country_iso_alp2"); // 
	        	 String notice_id  = (String) worldcorona.get("notice_id"); //
	        	 String title  = (String) worldcorona.get("title");   //
	        	 String txt_origin_cn  = (String) worldcorona.get("txt_origin_cn");  //
	        	 String html_origin_cn  = (String) worldcorona.get("html_origin_cn"); //
	        	
	        	
	        	 StringBuffer sb2 = new StringBuffer();
   	 
	        	 
	        	 worldcor.add(new CorDTO(resultCode, resultMsg, numOfRows,  pageNo,  totalCount, currentCount,country_nm,country_eng_nm,country_iso_alp2,notice_id,title,txt_origin_cn,html_origin_cn));
	        	// System.out.println(worldcor);
	        }        
	      }catch (Exception e) {
	          e.printStackTrace();
	      }	  
	return worldcor;
	}
	
	
	
	
	
	
}
