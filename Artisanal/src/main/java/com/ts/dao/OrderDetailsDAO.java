package com.ts.dao;

import java.util.List;

import com.ts.db.HibernateTemplate;
import com.ts.dto.OrderDetails;
import com.ts.dto.Orders;

public class OrderDetailsDAO {

	public int register(OrderDetails orderDetails) {
		// TODO Auto-generated method stub
		return HibernateTemplate.addObject(orderDetails);
	}

	public List<OrderDetails> getAllProducts(int orderId) {
		// TODO Auto-generated method stub
		List<OrderDetails> list = (List)HibernateTemplate.getObjectByOrderDId(orderId);
		return list;
	}

	public OrderDetails getOrderId(int orderDetailId) {
		return (OrderDetails)HibernateTemplate.getObjectByodId(orderDetailId);
	}

	public void delete(OrderDetails orderDetails1) {
		// TODO Auto-generated method stub
		HibernateTemplate.deleteObject(orderDetails1);
		
	}

	public void deleteObjects(int orderId) {
		// TODO Auto-generated method stub
		HibernateTemplate.deleteObjects(orderId);
		
	}

	
}
//	public int registerList(List<OrderDetails> orderDetailsList) {
//		// TODO Auto-generated method stub
//		
//		for(int i = 0; i < orderDetailsList.size(); i++){
//			System.out.println(orderDetailsList.get(i) + "\n");
//		}
//		System.out.println(orderDetailsList.size());
//		int result = 0;
//		for(int i = 0; i < orderDetailsList.size(); i++){
//			result += HibernateTemplate.addObject(orderDetailsList.get(i));
//		}
//		if(result == orderDetailsList.size())
//			return 1;
//		else
//			return 0;
//		
//	}


