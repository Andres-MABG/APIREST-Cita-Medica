package com.example.mi_cita_medica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.repositorios")
@EntityScan(basePackages = "com.example.entidades")
@ComponentScan(basePackages = {"com.example.controladores","com.example.config","com.example.dto","com.example.servicios","com.example.repositorios","com.example.entidades","com.example.DTO"})
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
