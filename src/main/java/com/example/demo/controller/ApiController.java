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

import com.example.demo.dto.AlrtDTO;
import com.example.demo.dto.CorDTO;
import com.example.demo.dto.EmbDTO;
import com.example.demo.dto.VisaDTO;
import com.example.demo.model.EmbEntity;

import com.example.demo.service.EmbService;


import lombok.extern.slf4j.Slf4j;


@CrossOrigin
@RestController
@RequestMapping("travinfo")
@Slf4j
public class ApiController {

	

	
	
	@Autowired
	EmbService service2;
	
	
	
	//@CrossOrigin(origins="http://localhost:3000/")
	@GetMapping("/embinfo")
	public ArrayList<EmbDTO> apitest(){	
		 List<EmbDTO> embL = service2.list();
		 
		   try {
			  
		    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/EmbassyService2/getEmbassyList2"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=G3AQbacYcF%2BgJmIYRBbImXEt49jd8jLdzAYjZb%2FF%2Fe%2BhFeUeT3JS3qRIgwhmuhI16uhhqlXfFJ3Uf2K61USaQw%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
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
		      //  System.out.println(sb.toString()); // 자바로 받아 오는건 확인됨 x, 아침에는 됬는데 그지같은게 갑자기 XML형식으로 받아옴 ㅗㅗㅗㅗ//0608 10시 다시 JSON으로 받아옴
		      
		        //json 파싱 연습
		        JSONParser parser = new JSONParser();
		        JSONObject obj = (JSONObject)parser.parse(result);
		        
		        JSONArray parse_listArr = (JSONArray)obj.get("data");
		       //초기화 시켜주기
	  
		         embL.clear();
		        
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

		        	 embL.add(new EmbDTO(iso, co_nm, em_k_nm,  em_addr,  tel_no, ur_tel));

		        }
	        
		      }catch (Exception e) {
		          e.printStackTrace();
		      }
	 
		return (ArrayList<EmbDTO>) embL;
	}
	
	static ArrayList<CorDTO> worldcor = new ArrayList<CorDTO>();
	
	//@CrossOrigin(origins="http://localhost:3000/") //리액트 연결했을때 코스 오리진 오류 해결
	@GetMapping("/worcorinfo")
	public ArrayList<CorDTO> apitest2(){
		
		try {
	    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryOverseasArrivalsService/getCountryOverseasArrivalsList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=G3AQbacYcF%2BgJmIYRBbImXEt49jd8jLdzAYjZb%2FF%2Fe%2BhFeUeT3JS3qRIgwhmuhI16uhhqlXfFJ3Uf2K61USaQw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
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
	     //   System.out.println(sb.toString()); // 자바로 받아 오는건 확인됨 x, 아침에는 됬는데 그지같은게 갑자기 XML형식으로 받아옴 ㅗㅗㅗㅗ//0608 10시 다시 JSON으로 받아옴
	      
	        //json 파싱 연습
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(result);
	        
	        JSONArray parse_listArr = (JSONArray)obj.get("data");
	       //초기화 시켜주기
	    
	        worldcor.clear();
	        
	      //  System.out.println(parse_listArr.size());
	        for (int i=0;i< parse_listArr.size();i++) {
	        	 JSONObject worldcorona = (JSONObject) parse_listArr.get(i);

	        	 String country_nm  = (String) worldcorona.get("country_nm"); // -----

	        	 String notice_id  = (String) worldcorona.get("notice_id"); //
	        	 String title  = (String) worldcorona.get("title");   //
	        	 String txt_origin_cn  = (String) worldcorona.get("txt_origin_cn");  //
	        	 String html_origin_cn  = (String) worldcorona.get("html_origin_cn"); //
	        	
	        	
	        	 StringBuffer sb2 = new StringBuffer();
   	 
	        	 
	        	 worldcor.add(new CorDTO(country_nm,txt_origin_cn));
	        	// System.out.println(worldcor);
	        }        
	      }catch (Exception e) {
	          e.printStackTrace();
	      }	  
	return worldcor;
	}
	
	
	
	static ArrayList<VisaDTO> wovisa = new ArrayList<VisaDTO>();
	
	
	
	@GetMapping("/visa")
	public ArrayList<VisaDTO> apitest3(){
		
		try {
	    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/EntranceVisaService2/getEntranceVisaList2"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=G3AQbacYcF%2BgJmIYRBbImXEt49jd8jLdzAYjZb%2FF%2Fe%2BhFeUeT3JS3qRIgwhmuhI16uhhqlXfFJ3Uf2K61USaQw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
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
	        //System.out.println(sb.toString()); // 자바로 받아 오는건 확인됨 x, 아침에는 됬는데 그지같은게 갑자기 XML형식으로 받아옴 ㅗㅗㅗㅗ//0608 10시 다시 JSON으로 받아옴
	      
	        //json 파싱 연습
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(result);
	        
	        JSONArray parse_listArr = (JSONArray)obj.get("data");
	       //초기화 시켜주기
	    
	        wovisa.clear();
	        
	      //  System.out.println(parse_listArr.size());
	        for (int i=0;i< parse_listArr.size();i++) {
	        	 JSONObject visa = (JSONObject) parse_listArr.get(i);
	        	 String country_nm  = (String) visa.get("country_nm"); // -----
	        	 String have_yn  = (String) visa.get("have_yn"); //
	        	 String gnrl_pspt_visa_yn  = (String) visa.get("gnrl_pspt_visa_yn");   //gnrl_pspt_visa_yn
	        	 String gnrl_pspt_visa_cn  = (String) visa.get("gnrl_pspt_visa_cn"); //일반 여권	        	 
	       
	        	 String remark  = (String) visa.get("remark"); //
	        	
	        	
	        	 StringBuffer sb2 = new StringBuffer();
   	 
	        	 
	        	 wovisa.add(new VisaDTO(country_nm,have_yn,gnrl_pspt_visa_yn,gnrl_pspt_visa_cn,remark));
	        	// System.out.println(worldcor);
	        }        
	      }catch (Exception e) {
	          e.printStackTrace();
	      }	  
	return wovisa;
	}
	
	
	static ArrayList<AlrtDTO> alrt = new ArrayList<AlrtDTO>();
	
	@GetMapping("/alrt")
	public ArrayList<AlrtDTO> apitest4(){
		
		try {
	    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryHistoryService2/getCountryHistoryList2"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=G3AQbacYcF%2BgJmIYRBbImXEt49jd8jLdzAYjZb%2FF%2Fe%2BhFeUeT3JS3qRIgwhmuhI16uhhqlXfFJ3Uf2K61USaQw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("400", "UTF-8")); /*한 페이지 결과 수*/
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
	       	      
	        //json 파싱 연습
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(result);
	        
	        JSONArray parse_listArr = (JSONArray)obj.get("data");
	       //초기화 시켜주기
	    
	        alrt.clear();
	        
	      //  System.out.println(parse_listArr.size());
	        for (int i=0;i< parse_listArr.size();i++) {
	        	 JSONObject alrtt = (JSONObject) parse_listArr.get(i);
	        	 String country_nm  = (String) alrtt.get("country_nm"); // -----
	        	 String title  = (String) alrtt.get("title"); //
	        	 String txt_origin_cn  = (String) alrtt.get("txt_origin_cn");   //
	        	 String wrt_dt  = (String) alrtt.get("wrt_dt"); //  	 

	        	
	        	
	        	 StringBuffer sb2 = new StringBuffer();
   	 
	        	 
	        	 alrt.add(new AlrtDTO(country_nm,title,txt_origin_cn,wrt_dt));

	        }        
	      }catch (Exception e) {
	          e.printStackTrace();
	      }	  
	return alrt;
	}
	
	
}
