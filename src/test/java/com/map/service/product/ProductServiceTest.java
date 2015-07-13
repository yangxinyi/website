package com.map.service.product;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.map.model.product.Product;
import com.map.service.BaseTest;

/**
 * 
 * @author yangxinyi
 *
 */
public class ProductServiceTest extends BaseTest {
	
	Product product;

	@Before
	public void setUp() {
		product = services.productService.get(1L);
	}

	@Test
	public void testSave() {
		product = new Product();
		product.setName("测试");
		product.setBrandId(5L);
		product.setPath("tmp");
		product.setProductGenreId(2L);
		product.setPrice(new BigDecimal(0.5));
		services.productService.save(product);
	}
	
	@Test
	public void testGet() {
		assertEquals("冰原狼徽章", product.getName());
	}
}