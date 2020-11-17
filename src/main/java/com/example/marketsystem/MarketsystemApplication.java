package com.example.marketsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.marketsystem.mapper")
@EnableTransactionManagement
public class MarketsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketsystemApplication.class, args);
	}

}
