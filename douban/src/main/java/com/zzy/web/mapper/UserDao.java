package com.zzy.web.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zzy.web.entity.UserTest;

@Repository("userDao")
public interface UserDao {

	@Select("select * from user where u_id=#{id}")
	@Results({ @Result(id = true, property = "id", column = "u_id"),
			@Result(property = "password", column = "u_passwd") })
	UserTest finOne(String id);

	@Select("select * from user where u_id=#{id} and u_passwd=#{password}")
	@Results({ @Result(id = true, property = "id", column = "u_id"),
			@Result(property = "password", column = "u_passwd") })
	UserTest findByidAndPasswd(@Param("id") String id, @Param("password") String password);
}
