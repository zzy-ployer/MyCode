package com.zzy.web.service;

import com.zzy.web.entity.UserTest;

public interface UserService {
	
	UserTest findOne(String id);

	UserTest findByidAndPasswd(String id, String password);
}
