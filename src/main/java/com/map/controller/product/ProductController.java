package com.map.controller.product;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.map.model.product.Product;
import com.map.service.product.ProductService;

/**
 * 
 * @author yangxinyi
 * 娴嬭瘯鐢�
 */
@Controller
@RequestMapping(value="product")
public class ProductController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView("/product_index");
		Product product = new Product();
		product.setName("页面测试");
		product.setBrandId(5L);
		product.setPath("tmp");
		product.setProductGenreId(2L);
		product.setPrice(new BigDecimal(0.5));
		productService.save(product);
		modelAndView.addObject("product_id", product.getId());
		return modelAndView;
	}
	
}