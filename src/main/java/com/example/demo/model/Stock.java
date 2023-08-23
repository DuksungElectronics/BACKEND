package com.example.demo.model;

public class Stock {
	
	private int market_id;
	private int item_id;
	private String item_name;
	private String item_price;
	private String item_stock;
	
	public Stock( String item_stock) {
		this.item_stock = item_stock;
	}
	
	/*
	public Stock(int market_id, int item_id, String item_name, String item_price, String item_stock) {
		super();
		this.market_id = market_id;
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_price = item_price;
		this.item_stock = item_stock;
	}*/
	//private List Stock;
	
	public int getMarket_id() {
		return market_id;
	}
	public void setMarket_id(int market_id) {
		this.market_id = market_id;
	}
	public int getItem_id() {
	
		return item_id;
	}	
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}	
	public String getItem_name() {
		return item_name;
	}	
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}	
	public String getItem_price() {
		return item_price;
	}
	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}
	public String getItem_stock() {
		
		return item_stock;
	}
	public void setItem_stock(String item_stock) {
		this.item_stock = item_stock;
	}
		
}