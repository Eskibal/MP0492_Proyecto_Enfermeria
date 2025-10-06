package com.example.enfermeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class EnfermeriaApplication {
	public static ArrayList<Nurse> nurseList = new ArrayList<Nurse>();

	public static void main(String[] args) {
		SpringApplication.run(EnfermeriaApplication.class, args);

		nurseList.add(new Nurse("Maria01", "Maria", "123"));
		nurseList.add(new Nurse("Irene02", "Irene", "123"));
		nurseList.add(new Nurse("Claudia03", "Claudia", "123"));
		nurseList.add(new Nurse("Anna04", "Anna", "123"));
		nurseList.add(new Nurse("Cristina05", "Cristina", "123"));

	}

    @GetMapping("/mainTest")
    public ResponseEntity<String> mainTest(){
    	return ResponseEntity.ok("The system is working fine!");
    }
}
