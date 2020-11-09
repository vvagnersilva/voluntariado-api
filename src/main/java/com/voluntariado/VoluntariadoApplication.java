package com.voluntariado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.voluntariado.utils.SenhaUtils;

// Documentação Swagger - http://localhost:8080/swagger-ui.html
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class VoluntariadoApplication extends SpringBootServletInitializer {

	private static Class<VoluntariadoApplication> applicationClass = VoluntariadoApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(VoluntariadoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(applicationClass);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			String senhaEncoded1 = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded: " + senhaEncoded1);

			String senhaEncoded2 = SenhaUtils.gerarBCrypt("123456");
			System.out.println("Senha encoded novamente: " + senhaEncoded2);

			System.out.println("Senha válida hash 1: " + SenhaUtils.senhaValida("123456", senhaEncoded1));
			System.out.println("Senha válida: hash 2: " + SenhaUtils.senhaValida("123456", senhaEncoded2));
		};
	}
}
