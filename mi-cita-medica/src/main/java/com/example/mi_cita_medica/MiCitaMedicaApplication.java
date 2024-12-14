package com.example.mi_cita_medica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.example.controladores")
@RestController
public class MiCitaMedicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiCitaMedicaApplication.class, args);
	}

	@GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }
}
