package org.diary.web.page.model;

import java.util.List;

import lombok.Data;

@Data
public class ResponseVO {
	
	private List<?> rows;
	private int total;
	
}