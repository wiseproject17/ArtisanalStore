package com.ts.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity

public class Agent {

	@Id
	@GeneratedValue
	private int agentId;
	private String firstName;
	private String lastName;
	private String emailId;
	private int phone;
	private String street;
	private String city;
	private String dealingVillage;
	private String password;
	
	@OneToMany(mappedBy="agent",fetch = FetchType.LAZY)
	List<Product> productList =new ArrayList<Product>();

	public Agent() {
		super();
	}
	
	
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDealingVillage() {
		return dealingVillage;
	}
	public void setDealingVillage(String dealingVillage) {
		this.dealingVillage = dealingVillage;
	}
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Agent [agentId=" + agentId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", phone=" + phone + ", street=" + street + ", city=" + city + ", dealingVillage="
				+ dealingVillage + ", password = " + password + "]";
	}
	
	
	
}