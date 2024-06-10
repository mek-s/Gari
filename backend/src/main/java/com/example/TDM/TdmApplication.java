package com.example.TDM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TdmApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdmApplication.class, args);
	}

}
