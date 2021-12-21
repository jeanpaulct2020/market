package com.mycompany.market.domain;

import java.util.List;

public class Client {

	private String clientId;
	private String name;
	private String surname;
	private Long phone;	
	private String address;
	private String email;
	private boolean active;
	//private List<Purchase> purchases;
	
	public Client() {
		super();
	}
	
	public Client(String clientId, String name, String surname, Long phone, String address, String email,
			boolean active) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.active = active;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
