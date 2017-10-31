package com.blog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.blog.exception.DataException;
import com.blog.model.UserEbo;

public interface IUserMapper {

	// 插入用户
	@Insert("INSTER INTO t_user " + " VALUES( " + "null,#{userName},#{nickName},#{password},#{email}"
			+ ",#{mobileNum},#{userType},#{status})")
	@Options(useGeneratedKeys=true, keyProperty="uid", keyColumn="uid")
	public Integer addUser(UserEbo user) throws DataException;

	// 通过uid查询用户
	@Select("   SELECT  "
			+ "      u.uid,u.user_name,u.nick_name,u.nick_name,u.nick_name,u.mobile_num,u.user_type,u.status "
			+ " FROM "
			+ "      t_user AS u " 
			+ " WHERE " 
			+ "      u.uid=#{uid}")
	@Results({
			@Result(property = "userName", column = "user_name", jdbcType = JdbcType.VARCHAR, javaType = String.class),
			@Result(property = "nickName", column = "nick_name", jdbcType = JdbcType.VARCHAR, javaType = String.class),
			@Result(property = "password", column = "pass_word", jdbcType = JdbcType.VARCHAR, javaType = String.class),
			@Result(property = "mobileNum", column = "mobile_num", jdbcType = JdbcType.VARCHAR, javaType = String.class),
			@Result(property = "userType", column = "user_type", jdbcType = JdbcType.CHAR, javaType = String.class),
			})
	public UserEbo getUserById(@Param("uid") Integer uid) throws DataException;
}
