package com.yagn.nadrii.service.domain;

import java.sql.Date;



public class User {
	
	///Field
	private String userId;
	private String userName;
	private String password;
	private String role;
	private String phone;
	private String email;
	private Date regDate;
	private String birth;
	private String profileImageFile;
	private String qrCode;
	private String children;
	private String gender;

	private String phone1;
	private String phone2;
	private String phone3;
	private String checkSuccess;
	
	/////////////////////
	private String modalUserId;
	private String modalUserPw;
	private String modalUserEmail;
	
	
	
	
	
	///Constructor
	public User(){
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
		String[] temp = phone.split("-");
		this.phone1 = temp[0];
		this.phone2 = temp[1];
		this.phone3 = temp[2];
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getProfileImageFile() {
		return profileImageFile;
	}
	public void setProfileImageFile(String profileImageFile) {
		this.profileImageFile = profileImageFile;
	}

	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public String getPhone3() {
		return phone3;
	}

	
	
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
		
	public String getCheckSuccess() {
		return checkSuccess;
	}
	public void setCheckSuccess(String checkSuccess) {
		this.checkSuccess = checkSuccess;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	
	
	
	
	public String getModalUserId() {
		return modalUserId;
	}
	public void setModalUserId(String modalUserId) {
		this.modalUserId = modalUserId;
	}
	public String getModalUserPw() {
		return modalUserPw;
	}
	public void setModalUserPw(String modalUserPw) {
		this.modalUserPw = modalUserPw;
	}
	public String getModalUserEmail() {
		return modalUserEmail;
	}
	public void setModalUserEmail(String modalUserEmail) {
		this.modalUserEmail = modalUserEmail;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", phone=" + phone + ", email=" + email + ", regDate=" + regDate + ", birth="
				+ birth + ", profileImageFile=" + profileImageFile + ", qrCode=" + qrCode + ", children=" + children
				+ ", gender=" + gender + ", phone1=" + phone1 + ", phone2=" + phone2 + ", phone3=" + phone3
				+ ", checkSuccess=" + checkSuccess + ", modalUserId=" + modalUserId + ", modalUserPw=" + modalUserPw
				+ ", modalUserEmail=" + modalUserEmail + "]";
	}
	
	
	
	
	
	
	
}