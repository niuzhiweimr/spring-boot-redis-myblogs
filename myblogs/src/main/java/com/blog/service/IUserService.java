package com.blog.service;

import com.blog.eto.UserEto;
import com.blog.exception.DataException;
import com.blog.model.UserEbo;

public interface IUserService {

	// 通过uid获取用户
	public UserEbo getUserById(Integer uid) throws DataException;

	// 用户注册
	public Integer regUser(UserEto user) throws DataException;
}
