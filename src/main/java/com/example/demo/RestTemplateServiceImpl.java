package com.example.demo;

import java.net.URI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Stock2;
import com.example.demo.service.RestTemplateService;

public class RestTemplateServiceImpl implements RestTemplateService{

	JSONObject object;
	@Override
	public String getReal_stock(String item_id) {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/real_stock/"+item_id)
				.encode()
				.build()
				.toUri();
		
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

	 
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(response.getBody());
			object = obj;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = object.get("item_stock").toString();

		return result;
		
	}
	
	@Override
	public String getCam_stock(String item_id) {
		
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:8080/stock/"+item_id)
				.encode()
				.build()
				.toUri();
		
		
		RestTemplate restTemplate = new RestTemplate();
	
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		
	 
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(response.getBody());
			object = obj;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String result = object.get("item_stock").toString();

		return result;
	}
	
	

	@Override
	public String getItem_price() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getItem_stock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMarket_id() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItem_id() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}

