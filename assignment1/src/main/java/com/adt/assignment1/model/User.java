package com.adt.assignment1.model;

import java.util.Date;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;


@Document(collection="User")
public class User {

	
	@Indexed
	private String username;
	@Indexed
	private String password;
	private String address;
	@Indexed
	private String mobileNumber;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
	private Date registrationDate;
	
	public User() {
		//empty constructor
	}
	
	public User( String username, String password, String address, String mobileNumber,Date registrationDate) {
		super();
		
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.registrationDate= registrationDate;
	}


	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", address=" + address + ", mobileNumber="
				+ mobileNumber + ", registrationDate=" + registrationDate + "]";
	}


	
	
	
	
}
