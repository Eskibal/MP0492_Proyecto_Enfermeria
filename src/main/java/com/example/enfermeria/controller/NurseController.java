package com.example.enfermeria.controller;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.enfermeria.dao.NurseRepository;
import com.example.enfermeria.entity.Nurse;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@Autowired
	private NurseRepository nurseRepository;

    public NurseController(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }
	
	@PostMapping("/login")
    public @ResponseBody boolean login(@RequestBody Nurse loginRequest) {		
		String user = loginRequest.getUser();
		String password = loginRequest.getPassword();
    	
		Optional<Nurse> nurse = nurseRepository.findByUser(user);
		if (nurse.isPresent() && nurse.get().getPassword().equals(password)) {
		    return true;
		}
		return false;
    }
	
	@GetMapping("/index")
	public @ResponseBody Iterable<Nurse> getAll()
	{
		return nurseRepository.findAll();
	}
	
	@GetMapping("/name")
    public ResponseEntity<Nurse> findByName(@RequestParam("name") String name) {
		try {
			Optional<Nurse> nurse = nurseRepository.findByNameIgnoreCase(name);

	        if (nurse.isPresent()) {
	            return ResponseEntity.ok(nurse.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
        
    }
	
	
	@PostMapping("/new")
	private ResponseEntity<Void> createNurse(@RequestBody Nurse newNurseRequest, UriComponentsBuilder ucb) {
		
		Nurse savedNurse = NurseRepository.save(newNurseRequest);

		URI locationOfNewNurse = ucb.path("nurse/{id}").buildAndExpand(savedNurse.id()).toUri();
		return ResponseEntity.created(locationOfNewNurse).build();
	}
	
	public void read() {
			
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
	
}