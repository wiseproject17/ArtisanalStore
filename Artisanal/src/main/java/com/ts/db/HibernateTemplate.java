package com.ts.db;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ts.dto.Agent;
import com.ts.dto.Customer;
import com.ts.dto.OrderDetails;
import com.ts.dto.Orders;
import com.ts.dto.Product;

public class HibernateTemplate {

	private static SessionFactory sessionFactory;
	
	static {
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	
	public static int addObject(Object obj)
	{
		System.out.println("Inside Template...");
		int result=0;
		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.save(obj);
			
			tx.commit();
			
			result=1;
			
		} catch (Exception e) {
		
			tx.rollback();
			
			e.printStackTrace();
		}
		
		return result;
	}
	public static void updateObject(Object obj) {
		
		Session session = sessionFactory.openSession();
		
		session.saveOrUpdate(obj);
		
		Transaction txn = session.beginTransaction();
		txn.commit();			
		session.close();
	}
	
	public static void deleteObject(Object obj) {
		
		Session session = sessionFactory.openSession();
		
		session.delete(obj);
		
		Transaction txn = session.beginTransaction();
		txn.commit();			
		session.close();
	}
	public static void deleteObjects(int orderId) {
		// TODO Auto-generated method stub
			String queryString = "delete OrderDetails where orderId = :orderId";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setParameter("orderId", orderId);
		  int result = query.executeUpdate();
		  
		  if (result > 0) {
		      System.out.println("orders was removed" + result);
		  }
	}
	
	public static Customer getObjectByUserPass(String loginId, String password) {
			String queryString = "from Customer where emailId = :loginId and password =:password";
			  Query query = sessionFactory.openSession().createQuery(queryString);
			  query.setString("loginId", loginId);
			  query.setString("password", password);
			  Object queryResult = query.uniqueResult();
			  Customer customer = (Customer)queryResult;
			  return customer; 
	}
	
	public static Agent getAgentByUserPass(String loginId, String password) {
		String queryString = "from Agent where emailId = :loginId and password =:password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("loginId", loginId);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  Agent agent = (Agent)queryResult;
		  return agent; 
}

	public static List<Object> getObjectListByQuery(String query)
	{
		
		return sessionFactory.openSession().createQuery(query).list();
	}
	

	public static List getObjectByCat(String catName) {
		// TODO Auto-generated method stub
			String queryString = "from Product where catName = :catName";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setParameter("catName", catName);
		  List<Product> list = query.list();
		return list;
	}
	public static Product getObjectById(int id) {
		  String queryString = "from Product where proId = :id";
		  Session session = sessionFactory.openSession();
		  Query query = session.createQuery(queryString);
		  query.setInteger("id", id);
		  Object queryResult = query.uniqueResult();
		  Product product = (Product)queryResult;
		  session.close();
		 // sessionFactory.close();
		  return product; 
	}

	public static List getObjectByOrderDId(int id) {
		  String queryString = "from OrderDetails where orderId = :id";
		  Session session = sessionFactory.openSession();
		  Query query = session.createQuery(queryString);
		  query.setParameter("id", id);
		  List<OrderDetails> list = query.list();
		  return list;
	}
	public static Customer getObjectByCustomerId(int customerId) {
		  String queryString = "from Customer where customerId = :customerId";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setParameter("customerId", customerId);
		  Object queryResult = query.uniqueResult();
		  Customer customer = (Customer)queryResult;
		  return customer; 
	}
	public static OrderDetails getObjectByodId(int orderDetailId) {
		  String queryString = "from OrderDetails where orderDetailId = :orderDetailId";
		  Session session = sessionFactory.openSession();
		  Query query = session.createQuery(queryString);
		  query.setParameter("orderDetailId", orderDetailId);
		  Object queryResult = query.uniqueResult();
		  OrderDetails orderDetails = (OrderDetails)queryResult;
		  session.close();
		  return orderDetails; 
	}
	
}