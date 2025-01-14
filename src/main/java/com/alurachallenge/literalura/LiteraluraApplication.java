package com.alurachallenge.literalura;

import com.alurachallenge.literalura.principal.Main;
import com.alurachallenge.literalura.repository.LiteraturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LiteraturaRepository repository;
	public static void main(String[] args) {SpringApplication.run(LiteraluraApplication.class, args);}

	public void run(String... args) throws Exception {
		Main main = new Main(repository);
		main.fnMenu();
	}
}
