package com.example.enfermeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enfermeria.entity.Nurse;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class EnfermeriaApplication {
	public static ArrayList<Nurse> nurseList = new ArrayList<Nurse>();

	public static void main(String[] args) {
		SpringApplication.run(EnfermeriaApplication.class, args);

		/*
		nurseList.add(new Nurse(1, "Maria01", "Maria", "123", "maria@gmail.com"));
		nurseList.add(new Nurse(2, "Irene02", "Irene", "123", "irene@gmail.com"));
		nurseList.add(new Nurse(3, "Claudia03", "Claudia", "123", "claudia@gmail.com"));
		nurseList.add(new Nurse(4, "Anna04", "Anna", "123", "ana@gmail.com"));
		nurseList.add(new Nurse(5, "Cristina05", "Cristina", "123", "cristina@gmail.com"));
		*/

	}

	//Method to test if Postman works
    @GetMapping("/mainTest")
    public ResponseEntity<String> mainTest(){
    	return ResponseEntity.ok("The system is working fine!");
    }
}
