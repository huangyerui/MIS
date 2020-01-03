package com.example.milktea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MilkteaApplication {
	@RequestMapping("/")
	public static String index(){
		return "数据库12345";
	}
	public static void main(String[] args) {
		SpringApplication.run(MilkteaApplication.class, args);
	}
}
