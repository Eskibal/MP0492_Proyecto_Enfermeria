package com.example.enfermeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EnfermeriaApplication {

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
