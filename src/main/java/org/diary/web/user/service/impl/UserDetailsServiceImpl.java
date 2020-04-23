package org.diary.web.user.service.impl;

import org.diary.web.user.mapper.UserMapper;
import org.diary.web.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = userMapper.loadUserByUserId(username);
		
		if (username.equals(user.getUserId())) {
			return user;
		}
		
		return null;
	}

}