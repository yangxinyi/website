package com.map.model.user;

import java.io.Serializable;

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
 * @version 1.0,2015-6-16
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 7205055003415828303L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
