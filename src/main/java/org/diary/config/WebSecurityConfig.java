package org.diary.config;

import org.diary.security.WebAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public WebAuthenticationProvider webAuthenticationProvider;
	
	@Autowired
	public AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**", "**/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/login/login").permitAll()
				.antMatchers("/login/join/**").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login/login")
				.loginProcessingUrl("/login/check")
				.successHandler(authenticationSuccessHandler)
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
				.logoutSuccessUrl("/login/login")
				.deleteCookies("JSESSIONID")
			.and()
			.authenticationProvider(webAuthenticationProvider);	
	}
	
}