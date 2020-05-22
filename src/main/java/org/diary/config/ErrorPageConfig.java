package org.diary.config;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageConfig implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); 
		
		if(status != null){ 
			int statusCode = Integer.valueOf(status.toString()); 
			
			if(statusCode == HttpStatus.NOT_FOUND.value()){ 
				return "redirect:/page/daily"; 
			} 
			
			if(statusCode == HttpStatus.FORBIDDEN.value()){ 
				return "500"; 
			} 
		}
		
		return "";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
