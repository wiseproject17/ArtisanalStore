package com.ts.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@Entity
public class  OrderDetails {
	
	@Id
	@GeneratedValue
	private int orderDetailId;
	private int quantity;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "orderId")
	private Orders orders;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "proId")
	private Product product;
	
	private double unitPrice;

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public Orders getOrders() {
		return orders;
	}

	
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

//	@Override
//	public String toString() {
//		return "OrderDetails [orderDetailId=" + orderDetailId + ", quantity=" + quantity + ", orders=" + orders
//				+ ", product=" + product + ", unitPrice=" + unitPrice + "]";
//	}
	
	
	
	
}