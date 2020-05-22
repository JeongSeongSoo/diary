package org.diary.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.diary.common.Common;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
public class WebAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {//implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		/*
		HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("url_prior_login");
            if (redirectUrl != null) {
                // we do not forget to clean this attribute from session
                session.removeAttribute("url_prior_login");
                // then we redirect
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
        */
		getRedirectStrategy().sendRedirect(request, response, Common.DEFAULT_PATH);
	}

}
