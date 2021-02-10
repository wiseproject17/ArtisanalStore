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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@XmlRootElement
public class Orders {
	
	@Id
	@GeneratedValue
	public int orderId;
	private String deliveryAddress;
	private long phoneNo;
	private int totalPrice;
	private String orderedDate;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@Cascade(CascadeType.DELETE)
	@JsonManagedReference
	@OneToMany(mappedBy="orders",fetch = FetchType.LAZY)
	List<OrderDetails> orderDetailsList =new ArrayList<OrderDetails>();

	public Orders() {
		
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

//	@Override
//	public String toString() {
//		return "Orders [orderId=" + orderId + ", deliveryAddress=" + deliveryAddress + ", phoneNo=" + phoneNo
//				+ ", totalPrice=" + totalPrice + ", orderedDate=" + orderedDate + ", customer=" + customer
//				+ ", orderDetailsList=" + orderDetailsList + "]";
//	}
//	
	
	
}