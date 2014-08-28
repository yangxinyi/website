package com.map.service.product;

import com.map.model.product.Product;

/**
 * 商品基础信息
 * 
 * @author yangxinyi
 * @version 1.0,2014-8-27
 */
public interface ProductService {

	void save(Product product);

	Product get(Long id);
	
}
