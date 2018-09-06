package com.nsit.testengine.user.dto;

import java.util.ArrayList;

public class UserDTO {
	private String fullName;
	private String userName;
	private String password;
	private String emailID;
	private String city;
	private String gender;
	private String roleName;
	private ArrayList<RightDTO> rightList;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public ArrayList<RightDTO> getRightList() {
		return rightList;
	}
	public void setRightList(ArrayList<RightDTO> rightList) {
		this.rightList = rightList;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
