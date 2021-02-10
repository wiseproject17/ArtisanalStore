package com.ts.dao;

import java.util.List;

import com.ts.db.HibernateTemplate;
import com.ts.dto.Orders;
import com.ts.dto.Product;

public class OrdersDAO {
	
	public int registerOrder(Orders orders) {
		// TODO Auto-generated method stub
		return HibernateTemplate.addObject(orders);
		
	}

	public void delete(Orders orders) {
		HibernateTemplate.deleteObject(orders);
		
	}

//	public List<Orders> getAllOrdersByCustId(int customerId) {
//		// TODO Auto-generated method stub
//		List<Orders> list = (List)HibernateTemplate.getObjectByOrderId(customerId);
//		return list;
//	}

}
