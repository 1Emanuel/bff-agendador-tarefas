package com.nztech.bbfagedadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BbfAgedadorTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbfAgedadorTarefasApplication.class, args);
	}

}
