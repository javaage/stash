package com.pactera.stash.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pactera.stash.Application;
import com.pactera.stash.mapper.service.StockService;
import com.pactera.stash.model.Stock;

public class QueryStash {
	
	@Autowired
	private StockService stockService;
	
	public static String doGet(String strUrl) throws Exception {
		URL url = new URL(strUrl);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setDoOutput(true);
		//urlConn.setDoInput(true);
		// urlConn.setUseCaches(false);
		// urlConn.setRequestProperty("Content-type","application/x-java-serialized-object");
		//urlConn.setRequestMethod("GET");
		
//		urlConn.setRequestProperty("contentType", "GBK");  
//		urlConn.setConnectTimeout(5 * 1000);  
//		urlConn.setRequestMethod("GET"); 
		
		urlConn.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));// 设置编码,否则中文乱码
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();

		urlConn.disconnect();
		return sb.toString();
	}
	
}
