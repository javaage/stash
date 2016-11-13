package com.pactera.stash.mapper.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.stash.Application;
import com.pactera.stash.mapper.StockMapper;
import com.pactera.stash.model.Record;
import com.pactera.stash.model.Stock;
import com.pactera.stash.model.StockExample;
import com.pactera.stash.util.QueryStash;


@Service
public class StockService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void insert(Stock stock) {
		SqlSession session = sqlSessionFactory.openSession();  
		StockMapper mapper= session.getMapper(StockMapper.class);  
		mapper.insert(stock);
		session.commit();
	}
	
	public void insertStocks(List<Stock> listStock) {
		SqlSession session = sqlSessionFactory.openSession();  
		StockMapper mapper= session.getMapper(StockMapper.class);  
		for(Stock stock: listStock){
			mapper.insert(stock);
		}
		session.commit();
	}

	public Stock getStockByCode(String strCode){
		SqlSession session = sqlSessionFactory.openSession();  
		StockMapper mapper= session.getMapper(StockMapper.class);
		return mapper.selectByPrimaryKey(strCode);
	}
	
	public int update(Stock stock){
		SqlSession session = sqlSessionFactory.openSession();  
		StockMapper mapper= session.getMapper(StockMapper.class); 
		return mapper.updateByPrimaryKey(stock);
	}
	
	public List<Stock> getAllStock(){
		SqlSession session = sqlSessionFactory.openSession();  
		StockMapper mapper= session.getMapper(StockMapper.class); 
		StockExample stockExample = new StockExample();
		return mapper.selectByExample(stockExample);
	}
	
	public void initQuery(){
		int maxCount = 435;
		List<Stock> listStock = getAllStock();
		int count = 0;
		StringBuffer sb = new StringBuffer();
		
		//= new ArrayList<String>();
		for(Stock stock : listStock){
			count++;
			sb.append(stock.getCode().toLowerCase() + ',');
			
			if(count >= maxCount){
				String strListCode = sb.toString();
				Application.arrUrlStock.add(String.format(Application.fomatterSina, strListCode.substring(0,strListCode.length()-1)));
				sb = new StringBuffer();
				count = 0;
			} 
		}
		if(count > 0){
			String strListCode = sb.toString();
			Application.arrUrlStock.add(String.format(Application.fomatterSina, strListCode.substring(0,strListCode.length()-1)));
		}
	}
	
	public void saveAllStock(){
		for(String urlStock :Application.arrUrlStock){
			
		}
	}
}
