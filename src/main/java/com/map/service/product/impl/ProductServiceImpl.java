package com.map.service.product.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.map.dao.ProductDao;
import com.map.model.product.Product;
import com.map.service.product.ProductService;
import com.norther.dynamic.dao.InjectDao;

/**
 * 商品基础信息
 * 
 * @author yangxinyi
 * @version 1.0,2014-8-27
 */
@Service("productService")
public class ProductServiceImpl implements ProductService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4771653058884218926L;

	private ProductDao productDao;

	@Override
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	public Product get(Long id){
		return productDao.get(id);
	}
	
	@InjectDao
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
}
