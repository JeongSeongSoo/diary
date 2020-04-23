package org.diary.web.user.controller;

import org.diary.web.user.model.UserVO;
import org.diary.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/join/userId")
	public ResponseEntity<Integer> loadUserByUserId(UserVO param) {
		int result = 1;
		
		UserVO vo = userService.loadUserByUserId(param);
		
		if (vo == null) {
			result = 0;
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/join/email")
	public ResponseEntity<Integer> loadUserByEmail(UserVO param) {
		int result = 1;
		
		UserVO vo = userService.loadUserByEmail(param);
		
		if (vo == null) {
			result = 0;
		}
		
		return ResponseEntity.ok(result);
	}
	
}
