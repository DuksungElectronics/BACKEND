package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.controller.StockController;
import com.example.demo.mapper.StockMapper;

@SpringBootApplication
public class Exercise2Application {
	
	

	
	
	public static void main(String[] args) {
		SpringApplication.run(Exercise2Application.class, args);
		
		//StockController controller = new StockController(null);
	}



}
