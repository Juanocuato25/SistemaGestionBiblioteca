package com.jarenas.msvc_loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcLoanApplication.class, args);
	}

}
