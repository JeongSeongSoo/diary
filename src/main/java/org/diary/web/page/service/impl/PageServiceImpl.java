package org.diary.web.page.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.diary.common.Common;
import org.diary.web.page.mapper.PageMapper;
import org.diary.web.page.model.ImgVO;
import org.diary.web.page.model.PageVO;
import org.diary.web.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	private PageMapper pageMapper;
	
	@Override
	public ImgVO addImg(MultipartFile mf) throws Exception {
		ImgVO param = new ImgVO();
		param.setContentType(mf.getContentType());
		param.setOriginNm(mf.getOriginalFilename());
		param.setNewNm(RandomStringUtils.randomAlphanumeric(20) + "_" + mf.getOriginalFilename());
		param.setImgPath(Common.PATH + "img/");
		param.setSize(mf.getSize());
		
		pageMapper.addImg(param);

		ImgVO img = pageMapper.loadImgByNewNm(param);
		
		File file = new File(img.getImgPath() + img.getNewNm());
		mf.transferTo(file);
		
		return img;
	}

	@Override
	public ImgVO loadImgByImgId(int imgId) {
		return pageMapper.loadImgByImgId(imgId);
	}

	@Override
	public void addPage(PageVO param) {
		pageMapper.addPage(param);
	}

	@Override
	public List<PageVO> loadPageByCategory(PageVO param) {
		return pageMapper.loadPageByCategory(param);
	}

	@Override
	public PageVO loadPageByPageId(int pageId) {
		return pageMapper.loadPageByPageId(pageId);
	}

	@Override
	public void modifyPage(PageVO param) {
		pageMapper.modifyPage(param);
	}

	@Override
	public void removePageByPageId(int pageId) {
		pageMapper.removePageByPageId(pageId);
	}

	@Override
	public int loadPageAll(PageVO param) {
		int total = pageMapper.loadPageAll(param); 
		total = (int) Math.ceil((double)total / Common.CNT);
		return total;
	}

}
