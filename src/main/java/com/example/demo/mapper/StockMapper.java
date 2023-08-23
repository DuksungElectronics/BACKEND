package com.example.demo.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Stock;
import com.example.demo.model.Stock2;
import com.example.demo.model.Theft;


@Mapper
public interface StockMapper {
	
	@Select("SELECT * FROM stock WHERE item_id=#{item_id}")
	Stock getStock(@Param("item_id") String item_id);
	
	@Update("UPDATE real_table SET item_stock = item_stock-1 WHERE item_id=#{item_id}")
	void updateStock(@Param("item_id") String item_id);
	
	@Select("SELECT item_stock FROM stock WHERE item_id=#{item_id}")
	Stock2 getStock2(@Param("item_id") String item_id);
	
	@Select("SELECT item_stock FROM real_table WHERE item_id=#{item_id}")
	Stock2 getRealStock(@Param("item_id") String item_id);
	
	@Update("UPDATE stock SET item_stock=${item_stock} WHERE item_id=${item_id} ")
	int updateCamStock(@Param("item_id") String item_id, @Param("item_stock") String item_stock);

	//page one fragment에서 사용하는 것.
	@Select("SELECT * FROM real_table")
	List<Stock> getStockList();
	
	@Update("INSERT INTO theft_table VALUES(1, #{theft_time})")
	void setTimeStamp(@Param("theft_time")String theft_time);
	
	//pate Two fragment에서 사용하는 것.
	@Select("SELECT theft_time FROM theft_table")
	List<Theft> getTheftList();
}	
	
