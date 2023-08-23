package com.example.demo.controller;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import com.example.demo.RestTemplateServiceImpl;
import com.example.demo.mapper.StockMapper;
import com.example.demo.model.Stock;
import com.example.demo.model.Stock2;
import com.example.demo.model.Theft;
import com.example.demo.service.RestTemplateService;
import com.example.demo.websocket.SocketTextHandler;
import com.example.demo.websocket.WebSocketSessionManager;

import java.io.IOException;
import java.net.http.WebSocket;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class StockController {
	
	private StockMapper mapper;
	
	//웹소캣 클래스 가져오기
	private WebSocketHandler webSocketHandler;

	//TimeStamp를 저장할 배열
	ArrayList<String> timeStamp = new ArrayList<>();
	
	//controller 생성자
	public StockController(StockMapper mapper) {
		this.mapper = mapper;	
		}
	
    
	//카매라table에서 stock 전부 다 가져오는 것.
	@GetMapping("/stock")
	public List<Stock> getStockList(){
		
		return mapper.getStockList();
	}
	
	//real Table stock 수정하는것.
	@PutMapping("/stock/update/{item_id}")
	public void updateStock(@PathVariable("item_id") String item_id){	   
	// 클라이언트에게 메시지 전송
		System.out.println("update stock");
		mapper.updateStock(item_id);
	}
	
	//CameraTable수정하기
	@PostMapping("/stock/cam/{item_id}")
	public void postStock(@PathVariable("item_id")String item_id, @RequestParam("item_stock")String item_stock) throws InterruptedException, IOException{
		mapper.updateCamStock(item_id, item_stock);
		
		//Template사용하기
		RestTemplateServiceImpl restServicimpl = new RestTemplateServiceImpl();
		//실재고 테이블(계산기와 연결된)에서 재고 가져오기
		
		String real_stock = restServicimpl.getReal_stock(item_id);
		
		
		Stock a_a = mapper.getStock(item_id);
		
		String a_a_a = a_a.toString();
		
		String cam_stock = restServicimpl.getCam_stock(item_id);
		
		SimpleDateFormat theft_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//timeStamp.add(sdf1.toString());
		//TextMessage textMessage = new TextMessage("안녕");
		SocketTextHandler k = new SocketTextHandler();
		
		//웹소켓에 
		//k.sendmessage(SocketTextHandler.id, sdf1.format(System.currentTimeMillis()));
		 
		//String date = sdf1.toString();
		//k.sendmessage(SocketTextHandler.id, "gods");
		
		
		
		if(real_stock.equals(cam_stock)) {
			//System.out.print("\n구매상황 발생\n");
		}
		else {
			//대기 시간 1분
			Thread.sleep(15000);
			String cam_stock2 = restServicimpl.getCam_stock(item_id);
			if(real_stock.equals(cam_stock2)) {
				System.out.print("구매상황 발생");
			}
			else {
				System.out.println("도난상황 발생");
				//도난 의심상황 시간대 DB에 기록
				setTimeStamp(theft_time.format(System.currentTimeMillis()));
				k.sendmessage(SocketTextHandler.id,theft_time.toString());
				
			}
			LocalDate now = LocalDate.now();
			LocalDate parisNow = LocalDate.now(ZoneId.of("Europe/Paris"));
			 System.out.print("\n"+theft_time.format(System.currentTimeMillis())+":");
			
			System.out.print("\n");
		
		}
	}
	
	@PutMapping("/theft/update")
	public void setTimeStamp(String theft_time)
	{
		SimpleDateFormat timestamp1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp2 = timestamp1.format(System.currentTimeMillis()).toString();
	    //mapper.setTimeStamp(timestamp2);
		// 클라이언트에게 메시지 전송
		
		mapper.setTimeStamp(timestamp2);
		
		//return mapper.setTimeStamp();
	}
	
	@GetMapping("/theft_time")
	public List<Theft> getTheftTimeList(){
		return mapper.getTheftList();
	}
	

	@GetMapping("/stock/{item_id}")
	public Stock getStock(@PathVariable("item_id") String item_id) {
		mapper.getStock(item_id);
		return mapper.getStock(item_id);		
	}
	
	@GetMapping("/stock/item_stock/{item_id}")
	public Stock2 getStock2(@PathVariable("item_id") String item_id) {
		mapper.getStock2(item_id);
		return mapper.getStock2(item_id);
	}
	
	
	//androidStudio 실재고 table과 연결된
	@GetMapping("/real_stock/{item_id}")
	public Stock2 getRealStock(@PathVariable("item_id") String item_id) {	
		return mapper.getRealStock(item_id);	
	}
	
	
	
}