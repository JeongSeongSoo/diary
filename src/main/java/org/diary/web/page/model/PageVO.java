package org.diary.web.page.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO extends CommonVO {

	private long pageId;
	private long imgId;
	private String title;
	private String content;
	private String category;

}
