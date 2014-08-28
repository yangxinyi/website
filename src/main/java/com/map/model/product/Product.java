package com.map.model.product;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品基础信息
 * 
 * @author yangxinyi
 * @version 1.0,2014-8-27
 */
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -2928320721936965410L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "productgenre_id")
    private Long productGenreId;

    @Column(name = "description")
    private String description;

    @Column(name = "path")
    private String path;

    @Column(name = "price")
    private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getProductGenreId() {
		return productGenreId;
	}

	public void setProductGenreId(Long productGenreId) {
		this.productGenreId = productGenreId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
