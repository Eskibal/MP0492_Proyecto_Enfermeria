package com.example.enfermeria;

public class Nurse {
	String usr;
	String pw;
	String name;
	
	public Nurse(String usr, String pw, String name) {
		this.usr = usr;
		this.pw = pw;
		this.name = name;
	}
	
	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}
