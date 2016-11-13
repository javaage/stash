package com.pactera.stash.mapper.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.stash.Application;
import com.pactera.stash.mapper.RecordMapper;
import com.pactera.stash.mapper.StockMapper;
import com.pactera.stash.model.Record;
import com.pactera.stash.model.Stock;
import com.pactera.stash.util.QueryStash;

@Service
public class RecordService {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void insert(Record record) {
		SqlSession session = sqlSessionFactory.openSession();
		RecordMapper mapper = session.getMapper(RecordMapper.class);
		mapper.insert(record);
		session.commit();
	}

	public void insertRecords(List<Record> listRecord) {
		SqlSession session = sqlSessionFactory.openSession();
		RecordMapper mapper = session.getMapper(RecordMapper.class);
		for (Record record : listRecord) {
			mapper.insert(record);
		}
		session.commit();
	}

	public void saveRecords(String urlStock) throws Exception {
		String strResults = QueryStash.doGet(urlStock);
		String[] arrResults = strResults.split(";");
		List<Record> listRecord = new ArrayList<Record>();
		for (String strResult : arrResults) {
			Record record = transferRecord(strResult);
			if (record != null) {
				listRecord.add(record);
			}
		}
		insertRecords(listRecord);
	}

	public Record transferRecord(String strResult) {
		try {
			if (strResult.length() < 100)
				return null;
			String code = strResult.substring(11, 19);
			strResult = strResult.substring(21, strResult.length() - 2);
			String[] items = strResult.split(",");
			if (items.length > 30) {
				String strTime = items[30] + ' ' + items[31].substring(0, 6) + "00";
				double col = Double.parseDouble(items[9]);
				if (col == 0)
					return null;
				Record record = new Record();
				record.setCode(code);
				record.setIndex(Double.parseDouble(items[3]));
				record.setTime(Application.dateFormat.parse(strTime));
				record.setdCol(col);
				return record;
			}
		} catch (Exception exp) {
		}
		return null;
	}
}
