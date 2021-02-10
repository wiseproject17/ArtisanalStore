package com.ts.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ts.db.HibernateTemplate;
import com.ts.dto.Customer;

public class CustomerDAO {
	private SessionFactory factory = null;
	
	public int register(Customer customer) {		
		return HibernateTemplate.addObject(customer);
	}
	public Customer getCustomerByUserPass(String loginId, String password) {
			
			return (Customer)HibernateTemplate.getObjectByUserPass(loginId,password);
		}
	public void updateCustomer(Customer customer) {
		 HibernateTemplate.updateObject(customer);
	}
	public Customer getAllOrdersByCustId(int customerId) {
		return (Customer)HibernateTemplate.getObjectByCustomerId(customerId);
	}
}
