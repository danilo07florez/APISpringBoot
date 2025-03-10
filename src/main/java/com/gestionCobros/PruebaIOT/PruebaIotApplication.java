package com.gestionCobros.PruebaIOT;

import com.gestionCobros.PruebaIOT.entity.DocumentoCobro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class PruebaIotApplication {

	public static void main(String[] args) {

		SpringApplication.run(PruebaIotApplication.class, args);
	}



}
