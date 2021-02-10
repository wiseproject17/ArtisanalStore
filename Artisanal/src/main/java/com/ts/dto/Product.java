package com.ts.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("orderDetailsList")
@XmlRootElement
@Entity

public class Product {
	
	@Id
	@GeneratedValue
	private int proId;
	private String proName;
	private double price;
	private String description;
	private String proImage;
	private String catName;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "agentId")
	private Agent agent;
	
	@JsonBackReference
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
	private List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
	
	
	public Product() {
		super();
	}


	public int getProId() {
		return proId;
	}


	public void setProId(int proId) {
		this.proId = proId;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getProImage() {
		return proImage;
	}


	public void setProImage(String proImage) {
		this.proImage = proImage;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Agent getAgent() {
		return agent;
	}


	public void setAgent(Agent agent) {
		this.agent = agent;
	}


	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}


	public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}


	@Override
	public String toString() {
		return "Product [proId=" + proId + ", proName=" + proName + ", price=" + price + ", description=" + description
				+ ", proImage=" + proImage + ", catName=" + catName + ", quantity=" + quantity + ", agent=" + agent
				+ ", orderDetailsList=" + orderDetailsList + "]";
	}
	
	
	
	
}