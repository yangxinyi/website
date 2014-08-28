package com.map.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.map.service.product.ProductService;


/**
 * description
 * 
 * @author yangxinyi
 * @version 1.0,2014-08-28
 */
@Component
public class Services {
	
	@Autowired public ProductService productService;
	
}
