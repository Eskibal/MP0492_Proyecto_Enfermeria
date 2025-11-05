package com.example.enfermeria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNurse;
	private String name;
	private String user;
	private String password;
	private String email;	

	public int getIdNurse() {
		return idNurse;
	}

	public void setIdNurse(int idNurse) {
		this.idNurse = idNurse;
	}

	public String getUser() {
	    return user;
	}

	public void setUser(String user) {
	    this.user = user;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
