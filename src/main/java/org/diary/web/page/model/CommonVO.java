package org.diary.web.page.model;

import org.diary.common.Common;

public class CommonVO {
	
	private int start;   
	private int end;      
	private int pageNum; 
	private int total;   
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageNum() {
		return this.pageNum;
	}
	
	public int getStart() {
		this.start = (pageNum - 1) * Common.CNT;
		return this.start;
	}
	
	public int getEnd() {
		this.end = Common.CNT;
		return this.end;
	}
	
}
