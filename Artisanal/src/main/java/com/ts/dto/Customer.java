package com.ts.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("ordersList")
@XmlRootElement
@Entity
public class Customer {
	
	@Id@GeneratedValue
	private int  customerId;
	private String userName;
	private String emailId;
	private String password;
	private String conPassword;
	private int phone;
	private String address;
	private String state;
	private String district;
	
	@JsonBackReference
	@OneToMany(mappedBy="customer",fetch = FetchType.LAZY)
	List <Orders> ordersList = new ArrayList<Orders>();
	
	
	public Customer() {
		super();
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getConPassword() {
		return conPassword;
	}


	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public List<Orders> getOrdersList() {
		return ordersList;
	}


	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", userName=" + userName + ", emailId=" + emailId + ", password="
				+ password + ", phone=" + phone + ", address=" + address + ", state=" + state + ", district=" + district
				+ ", ordersList=" + ordersList + "]";
	}
	
	
	
}
