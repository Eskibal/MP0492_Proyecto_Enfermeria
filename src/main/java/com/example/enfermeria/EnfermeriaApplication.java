package com.example.enfermeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class EnfermeriaApplication {
	public static ArrayList<Nurse> nurseList = new ArrayList<Nurse>();

	public static void main(String[] args) {
		SpringApplication.run(EnfermeriaApplication.class, args);

		nurseList.add(new Nurse("m", "Marc", "123"));
		nurseList.add(new Nurse("p", "Patrick", "123"));
		nurseList.add(new Nurse("a", "Adam", "123"));
		nurseList.add(new Nurse("v", "Victor", "123"));
		nurseList.add(new Nurse("e", "Eric", "123"));

	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
