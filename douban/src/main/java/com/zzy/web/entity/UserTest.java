package com.zzy.web.entity;

public class UserTest {
	private String id;
	private String password;
	
	public UserTest() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserTest [id=" + id + ", password=" + password + "]";
	}
	
}
