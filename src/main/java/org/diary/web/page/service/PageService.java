package org.diary.web.page.service;

import java.util.List;

import org.diary.web.page.model.ImgVO;
import org.diary.web.page.model.PageVO;
import org.springframework.web.multipart.MultipartFile;

public interface PageService {

	ImgVO addImg(MultipartFile file) throws Exception;

	ImgVO loadImgByImgId(int imgId);

	void addPage(PageVO param);

	List<PageVO> loadPageByCategory(PageVO param);

	PageVO loadPageByPageId(int pageId);

	void modifyPage(PageVO param);

	void removePageByPageId(int pageId);

	int loadPageAll(PageVO param);

}
