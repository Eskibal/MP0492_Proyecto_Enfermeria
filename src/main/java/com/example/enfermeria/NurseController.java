package com.example.enfermeria;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NurseController {
	
	ArrayList<Nurse> nurseList = EnfermeriaApplication.nurseList;

	
	
	@GetMapping("/nurse/index")
	public ResponseEntity<ArrayList<Nurse>> getAll(){
		return ResponseEntity.ok(nurseList);
	}
	
	
	@GetMapping("/nurse/name")
	public ResponseEntity<Nurse> findByName(@RequestParam(value = "name", defaultValue = "") String name) {

		for (int i = 0; i < nurseList.size(); i++) {
			if (name.equalsIgnoreCase(nurseList.get(i).getName())) {
				return ResponseEntity.ok(nurseList.get(i));
			}

		}
		return ResponseEntity.notFound().build();
	}

}