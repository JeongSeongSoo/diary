package org.diary.web.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.diary.web.user.model.UserVO;

@Mapper
public interface UserMapper {
	
	UserVO loadUserByUserId(String userId);

	UserVO loadUserByEmail(UserVO param);

	void addUser(UserVO param);
	
}
