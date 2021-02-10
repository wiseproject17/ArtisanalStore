package com.ts.dao;

import java.util.List;

import com.ts.db.HibernateTemplate;
import com.ts.dto.Product;
import com.ts.dto.wishlist;

public class wishlistDAO {

	public void addfavorite(wishlist product) {
		 HibernateTemplate.addObject(product);
		
	}
	
	public List<wishlist> getAllFavorities() {
		// TODO Auto-generated method stub
		List<wishlist> list = (List)HibernateTemplate.getObjectListByQuery("From wishlist");	
		return list;
	}

}