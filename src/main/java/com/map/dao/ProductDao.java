package com.map.dao;

import com.map.model.product.Product;
import com.norther.dynamic.dao.annotation.Get;
import com.norther.dynamic.dao.annotation.Save;

/**
 * 
 * @author yangxinyi
 *
 */

public interface ProductDao {

	@Save
	void save(Product product);
	
	@Get
	Product get(Long id);
	
}
