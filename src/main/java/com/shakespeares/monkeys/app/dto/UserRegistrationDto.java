package com.shakespeares.monkeys.app.dto;


import java.math.BigDecimal;

public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private BigDecimal balance;

	public UserRegistrationDto() {

	}

	public UserRegistrationDto( String firstName, String lastName, String username, String password, BigDecimal balance) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
