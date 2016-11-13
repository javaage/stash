/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pactera.stash;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	
	public static final List<String> arrUrlStock = new ArrayList<String>();
	public static final String fomatterSina = "http://hq.sinajs.cn/list=%s";
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public DataSource dataSource() {
		PGPoolingDataSource pgpool = new PGPoolingDataSource();
		pgpool = new PGPoolingDataSource();
        pgpool.setDataSourceName("stash");
        pgpool.setServerName("localhost");
        pgpool.setDatabaseName("stash");
        pgpool.setUser("postgres");
        pgpool.setPassword("19786028");
        pgpool.setMaxConnections(10);
		return pgpool;
    }
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(){
		String resource = "/mybatis.xml";
		InputStream inputStream = Application.class.getResourceAsStream(resource);
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		return factory;
	}
}
