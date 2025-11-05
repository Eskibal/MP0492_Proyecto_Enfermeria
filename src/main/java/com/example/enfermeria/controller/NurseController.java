package com.example.enfermeria.controller;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public @ResponseBody ResponseEntity<Boolean> login(@RequestBody Nurse loginRequest) {		
		String user = loginRequest.getUser();
		String password = loginRequest.getPassword();
    	
		Optional<Nurse> nurse = nurseRepository.findByUser(user);
		if (nurse.isPresent() && nurse.get().getPassword().equals(password)) {
		    return ResponseEntity.ok(true);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
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
	
	
	@PostMapping("/{new}")
	private ResponseEntity<Void> createNurse(@RequestBody Nurse newNurseRequest, UriComponentsBuilder ucb) {
		
		Nurse savedNurse = nurseRepository.save(newNurseRequest);

		URI locationOfNewNurse = ucb.path("nurse/{id}").buildAndExpand(savedNurse.getIdNurse()).toUri();
		return ResponseEntity.created(locationOfNewNurse).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Nurse> findById(@PathVariable long requestedId)
	{
		Optional<Nurse> nurse = nurseRepository.findById(requestedId);
		if (nurse.isPresent()) {
			return ResponseEntity.ok(nurse.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// PUT requests that match nurse will be handled by this method
	@PutMapping("/{requestedId}")
	public ResponseEntity<Void> putNurse(@PathVariable long requestedId, @RequestBody Nurse nurseUpdate) {
	    Optional<Nurse> nurse = nurseRepository.findById(requestedId);
	    if (nurse.isPresent()) {
	        Nurse updatedNurse = nurse.get();
	        boolean valid = true;
	        
	        // name
	        if (nurseUpdate.getName() != null && 
	        	nurseUpdate.getName().trim().length() >= 3) {
	        	
	        	updatedNurse.setName(nurseUpdate.getName());
	        	
	        } else {
	        	valid = false;
	        }

	        // user
	        if (nurseUpdate.getUser() != null && 
	        	!nurseUpdate.getUser().trim().isEmpty()) {
	        	
		        updatedNurse.setUser(nurseUpdate.getUser());
		        
	        } else {
	        	valid = false;
	        }

	        // password
	        if (nurseUpdate.getPassword() != null &&
	            nurseUpdate.getPassword().length() >= 6 &&
	            nurseUpdate.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d).+$")) {
	        	
	            updatedNurse.setPassword(nurseUpdate.getPassword());
	            
	        } else {
	        	valid = false;
	        }

	        // email
	        if (nurseUpdate.getEmail() != null && 
	        	nurseUpdate.getEmail().matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")) {
	        	
	        	updatedNurse.setEmail(nurseUpdate.getEmail());
	        	
	        } else {
	        	valid = false;
	        }
	        
	        if(valid) {
	        	nurseRepository.save(updatedNurse);
	        	return ResponseEntity.ok().build();
	        } else {
	        	return ResponseEntity.badRequest().build();
	        }
	        
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
  
	@GetMapping
	public ResponseEntity<Iterable<Nurse>> findAll(Pageable pageable)
	{
		Page<Nurse> page = nurseRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))));
		
		return ResponseEntity.ok(page.getContent());
	}
	
	@DeleteMapping("/{requestedId}")
	private ResponseEntity<Void> delete(@PathVariable long requestedId) 
	{		
		if (nurseRepository.existsById(requestedId)) 
		{
			nurseRepository.deleteById(requestedId);
			return ResponseEntity.ok().build();
		} 
		else 
		{
			return ResponseEntity.notFound().build();
		}
	}
	
}