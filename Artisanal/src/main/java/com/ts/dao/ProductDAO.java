package com.ts.dao;

import java.util.List;

import com.ts.db.HibernateTemplate;
import com.ts.dto.Customer;
import com.ts.dto.Product;

public class ProductDAO {
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		return HibernateTemplate.addObject(product);
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> list = (List)HibernateTemplate.getObjectListByQuery("From Product");	
		return list;
	}

	public List<Product> getAllProductsByCat(String catName) {
		// TODO Auto-generated method stub
		return (List)HibernateTemplate.getObjectByCat(catName);
		//List<Product> list = (List)HibernateTemplate.getObjectListByQuery("From Product where catName = :catName ");
	}

	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		 HibernateTemplate.updateObject(product);
	}
	
	
	
}
