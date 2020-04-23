package org.diary.web.user.service.impl;

import org.diary.web.user.mapper.UserMapper;
import org.diary.web.user.model.UserVO;
import org.diary.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserVO loadUserByUserId(UserVO param) {
		return userMapper.loadUserByUserId(param.getUserId());
	}

	@Override
	public UserVO loadUserByEmail(UserVO param) {
		return userMapper.loadUserByEmail(param);
	}

	@Override
	public void addUser(UserVO param) {
		userMapper.addUser(param);
	}

}
