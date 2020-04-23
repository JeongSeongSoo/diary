package org.diary.security;

import java.util.ArrayList;

import org.diary.web.user.model.UserVO;
import org.diary.web.user.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
        String userPw = (String) authentication.getCredentials();

        UserVO userVO = (UserVO) userDetailsServiceImpl.loadUserByUsername(userId);
        if (userVO == null) {
        	throw new BadCredentialsException("Login Error !!");
        } else {
        	if (bCryptPasswordEncoder.matches(userPw, userVO.getUserPw())) {
        		userVO.setUserPw(null);
        	} else {
        		throw new BadCredentialsException("Login Error !!");
        	}
        }
 
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(userVO, null, authorities);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
