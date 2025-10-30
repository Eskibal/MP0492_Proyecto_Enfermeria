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

	public static void main(String[] args) 
	{
		SpringApplication.run(EnfermeriaApplication.class, args);

	}

	//Method to test if Postman works
    @GetMapping("/mainTest")
    public ResponseEntity<String> mainTest()
    {
    	return ResponseEntity.ok("The system is working fine!");
    }
}
