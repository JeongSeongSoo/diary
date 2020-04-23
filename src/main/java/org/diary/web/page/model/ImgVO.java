package org.diary.web.page.model;

import lombok.Data;

@Data
public class ImgVO {
	
	private long imgId;
	private String originNm;
	private String newNm;
	private String contentType;
	private String imgPath;
	private long size;
	
}