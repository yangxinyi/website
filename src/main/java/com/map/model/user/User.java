package com.map.model.user;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang.time.DateFormatUtils;

import com.support.util.ControllerConstant;
import com.support.web.filter.authentication.Principal;

/**
 * 用户信息
 * 
 * @author yangxinyi
 * @version 1.0,2015-6-16
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable, Principal {

	private static final long serialVersionUID = 7205055003415828303L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;
    
    @Column(name = "registertime")
	private Date registerTime;

	@Column(name = "lastlogintime")
	private Date lastLoginTime;
	
	@Column
	private boolean available;
	
	@Column
	private String email;
	
	@Column
	private String mobile;
    
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public Serializable getIdentity() {
		return getId();
	}

	@Override
	public Long getLastLoginSeconds() {
		try {
			Date date = getLastLoginTime();
			String now = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return format.parse(now).getTime();
		} catch (ParseException e) {
			return getLastLoginTime().getTime();
		}
	}

	@Override
	public String getDisplayName() {
		return name;
	}

	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getName()=" + getName() + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
         
	public boolean checkEmail(String email) {
		return email.matches(ControllerConstant.EMAIL_EXP);
	}
	
}
