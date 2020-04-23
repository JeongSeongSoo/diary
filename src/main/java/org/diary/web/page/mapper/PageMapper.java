package org.diary.web.page.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.diary.web.page.model.ImgVO;
import org.diary.web.page.model.PageVO;

@Mapper
public interface PageMapper {

	void addImg(ImgVO param);

	ImgVO loadImgByNewNm(ImgVO param);

	ImgVO loadImgByImgId(int imgId);

	void addPage(PageVO param);

	List<PageVO> loadPageByCategory(PageVO param);

	PageVO loadPageByPageId(int pageId);

	void modifyPage(PageVO param);

	void removePageByPageId(int pageId);

	int loadPageAll(PageVO param);

}
