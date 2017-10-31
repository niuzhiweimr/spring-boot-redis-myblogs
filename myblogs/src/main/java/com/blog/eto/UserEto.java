package com.blog.eto;

import com.blog.annotation.Validation;
import com.blog.constant.Constant;

public class UserEto {

	// 账号为必填
	@Validation
	private String userName;
	// 生成默认的昵称
	private String nickName;
	// 账号为必填
	@Validation
	private String password;
	private String email;
	private String mobileNum;
	// 默认为普通用户
	private String userType = Constant.USER_TYPE_ORDINARY;
	// 默认为有效状态
	private String status = Constant.FLAG_YES;

	public UserEto() {
		super();
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

}
