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
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*��������ȣ*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*�� ������ ��� ��*/
		        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML �Ǵ� JSON*/
		      
		        
		        Map<String, Object> Embmap = new HashMap<String, Object>();
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  //"UTF-8" �� �߰����ִϱ� �ѱ۷� �޾ƿԴ�(06-23)
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
		      //  System.out.println(sb.toString()); // �ڹٷ� �޾� ���°� Ȯ�ε� x, ��ħ���� ��µ� ���������� ���ڱ� XML�������� �޾ƿ� �ǤǤǤ�//0608 10�� �ٽ� JSON���� �޾ƿ�
		      
		        //json �Ľ� ����
		        JSONParser parser = new JSONParser();
		        JSONObject obj = (JSONObject)parser.parse(result);
		        
		        JSONArray parse_listArr = (JSONArray)obj.get("data");
		       //�ʱ�ȭ �����ֱ�
	  
		         embL.clear();
		        
		      //  System.out.println(parse_listArr.size());
		        for (int i=0;i< parse_listArr.size();i++) {
		        	 JSONObject Emb = (JSONObject) parse_listArr.get(i);
		        	 String c_tel_no = (String) Emb.get("center_tel_no"); // ��?�� null���� ��κ�  ty-cd�� 40�� ���� �� ��ȣ�� ����, ȣ�ֵ� ����
		        	 String eng_nm  = (String) Emb.get("country_eng_nm"); //���� �����
		        	 String iso  = (String) Emb.get("country_iso_alp2"); // ISO�ڵ�
		        	 String co_nm  = (String) Emb.get("country_nm"); // ���� �̸� -
		        	 String em_cd  = (String) Emb.get("embassy_cd" ); // �ڵ� ���� ����
		        	 String em_k_nm  = (String) Emb.get("embassy_kor_nm"); // ������ -
		        	 String em_lt  = String.valueOf( Emb.get("embassy_lat")); // ����
		        	 String em_ln  = String.valueOf( Emb.get("embassy_lng")); // �浵
		        	 String em_mn_ty_cd  = (String) Emb.get("embassy_manage_ty_cd"); // 1 -�Ϲ� , 2-����, 3-����,
		        	 String em_mn_ty_cd_nm  = (String) Emb.get("embassy_manage_ty_cd_nm"); //
		        	 String em_ty_cd  = (String) Emb.get("embassy_ty_cd"); // 10 -����, 20 -�����,30-��ǥ��,40- (�����ⱸ ��ǥ��..),50 -�а� ,60-(������� �����ΰ��װ���ǥ��),70-�����(�Ϲ�?),80- ���� ���а�,90-(�߸𸣰���),100-(�߸𸣰���),110-(�𸣰���),130- �����(��,�����)
		        	 String em_ty_cd_nm  = (String) Emb.get("embassy_ty_cd_nm"); //
		        	 String em_addr  = (String) Emb.get("emblgbd_addr");   //���� �ּ� -
		        	 String free_tel  = (String) Emb.get("free_tel_no");  //�� �ϴ� null���� ��κ� ty-cd�� 40�� ���� �� ��ȣ�� ����,ȣ�ֵ� ����
		        	 String tel_no  = (String) Emb.get("tel_no"); //���� ��ȭ ��ȣ -
		        	 String ur_tel  = (String) Emb.get("urgency_tel_no");  //��� ��ȭ -
		        	
		        	 StringBuffer sb2 = new StringBuffer();

		        	 embL.add(new EmbDTO(iso, co_nm, em_k_nm,  em_addr,  tel_no, ur_tel));

		        }
	        
		      }catch (Exception e) {
		          e.printStackTrace();
		      }
	 
		return (ArrayList<EmbDTO>) embL;
	}
	
	static ArrayList<CorDTO> worldcor = new ArrayList<CorDTO>();
	
	//@CrossOrigin(origins="http://localhost:3000/") //����Ʈ ���������� �ڽ� ������ ���� �ذ�
	@GetMapping("/worcorinfo")
	public ArrayList<CorDTO> apitest2(){
		
		try {
	    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryOverseasArrivalsService/getCountryOverseasArrivalsList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=G3AQbacYcF%2BgJmIYRBbImXEt49jd8jLdzAYjZb%2FF%2Fe%2BhFeUeT3JS3qRIgwhmuhI16uhhqlXfFJ3Uf2K61USaQw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*��������ȣ*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*�� ������ ��� ��*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML �Ǵ� JSON*/
	     
	        
	        Map<String, Object> Embmap = new HashMap<String, Object>();
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  //"UTF-8" �� �߰����ִϱ� �ѱ۷� �޾ƿԴ�(06-23)
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
	     //   System.out.println(sb.toString()); // �ڹٷ� �޾� ���°� Ȯ�ε� x, ��ħ���� ��µ� ���������� ���ڱ� XML�������� �޾ƿ� �ǤǤǤ�//0608 10�� �ٽ� JSON���� �޾ƿ�
	      
	        //json �Ľ� ����
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(result);
	        
	        JSONArray parse_listArr = (JSONArray)obj.get("data");
	       //�ʱ�ȭ �����ֱ�
	    
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
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*��������ȣ*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*�� ������ ��� ��*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML �Ǵ� JSON*/
	     
	        
	        Map<String, Object> Embmap = new HashMap<String, Object>();
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  //"UTF-8" �� �߰����ִϱ� �ѱ۷� �޾ƿԴ�(06-23)
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
	        //System.out.println(sb.toString()); // �ڹٷ� �޾� ���°� Ȯ�ε� x, ��ħ���� ��µ� ���������� ���ڱ� XML�������� �޾ƿ� �ǤǤǤ�//0608 10�� �ٽ� JSON���� �޾ƿ�
	      
	        //json �Ľ� ����
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(result);
	        
	        JSONArray parse_listArr = (JSONArray)obj.get("data");
	       //�ʱ�ȭ �����ֱ�
	    
	        wovisa.clear();
	        
	      //  System.out.println(parse_listArr.size());
	        for (int i=0;i< parse_listArr.size();i++) {
	        	 JSONObject visa = (JSONObject) parse_listArr.get(i);
	        	 String country_nm  = (String) visa.get("country_nm"); // -----
	        	 String have_yn  = (String) visa.get("have_yn"); //
	        	 String gnrl_pspt_visa_yn  = (String) visa.get("gnrl_pspt_visa_yn");   //gnrl_pspt_visa_yn
	        	 String gnrl_pspt_visa_cn  = (String) visa.get("gnrl_pspt_visa_cn"); //�Ϲ� ����	        	 
	       
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
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*��������ȣ*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("400", "UTF-8")); /*�� ������ ��� ��*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML �Ǵ� JSON*/
	     
	        
	        Map<String, Object> Embmap = new HashMap<String, Object>();
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));  //"UTF-8" �� �߰����ִϱ� �ѱ۷� �޾ƿԴ�(06-23)
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
	       	      
	        //json �Ľ� ����
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject)parser.parse(result);
	        
	        JSONArray parse_listArr = (JSONArray)obj.get("data");
	       //�ʱ�ȭ �����ֱ�
	    
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
