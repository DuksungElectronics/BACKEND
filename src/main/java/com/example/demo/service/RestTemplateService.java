package com.example.demo.service;

public interface RestTemplateService {

	public String getCam_stock(String item_id);
	public String getItem_price();
	public String getItem_stock();
	public int getMarket_id();
	public int getItem_id();
	public String getReal_stock(String item_id);
	
}
