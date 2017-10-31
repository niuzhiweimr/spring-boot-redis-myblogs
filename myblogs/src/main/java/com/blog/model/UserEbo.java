package com.blog.model;

import java.io.Serializable;

public class UserEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2486915169211614223L;
	private Integer uid;
	private String userName;
	private String nickName;
	private String password;
	private String email;
	private String mobileNum;
	private String userType;
	private String status;

	public UserEbo() {
		super();
	}

	public UserEbo(Integer uid, String userName, String nickName, String password, String email, String mobileNum,
			String userType, String status) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.nickName = nickName;
		this.password = password;
		this.email = email;
		this.mobileNum = mobileNum;
		this.userType = userType;
		this.status = status;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserEbo [uid=" + uid + ", userName=" + userName + ", nickName=" + nickName + ", password=" + password
				+ ", email=" + email + ", mobileNum=" + mobileNum + ", userType=" + userType + ", status=" + status
				+ "]";
	}

}
