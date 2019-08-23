package com.zzy.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zzy.web.entity.UserTest;
import com.zzy.web.mapper.UserDao;
import com.zzy.web.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;

	public UserTest findOne(String id) {

		return userDao.finOne(id);
	}

	@Override
	public UserTest findByidAndPasswd(String id, String password) {
		return userDao.findByidAndPasswd(id, password);
	}

}
