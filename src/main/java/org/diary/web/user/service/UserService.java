package org.diary.web.user.service;

import org.diary.web.user.model.UserVO;

public interface UserService {
	
	UserVO loadUserByUserId(UserVO param);

	UserVO loadUserByEmail(UserVO param);

	void addUser(UserVO param);
	
}
