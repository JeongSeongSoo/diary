package org.diary.web.page.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.diary.web.page.model.ImgVO;
import org.diary.web.page.model.PageVO;
import org.diary.web.page.model.ResponseVO;
import org.diary.web.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/page")
@RestController
public class PageRestController {

	@Autowired
	private PageService pageService;
	
	@PostMapping("/image/add")
	public ResponseEntity<?> addImg(@RequestParam("file") MultipartFile file, HttpServletRequest req, HttpServletResponse rep) throws Exception {
		ImgVO img = pageService.addImg(file);
		return ResponseEntity.ok().body("/page/image/" + img.getImgId());
	}
	
	@GetMapping("/image/{imgId}")
	public ResponseEntity<byte[]> loadImg(@PathVariable("imgId") int imgId) throws Exception {
		HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.IMAGE_JPEG);
        
        ImgVO img = pageService.loadImgByImgId(imgId);
        FileInputStream fis = new FileInputStream(new File(img.getImgPath() + img.getNewNm()));

	    return new ResponseEntity<byte[]>(IOUtils.toByteArray(fis), header, HttpStatus.CREATED);
	}
	
	@GetMapping("/category/{category}/{pageNum}/load")
	public ResponseEntity<?> loadPageByCategory(@PathVariable("category") String category, @PathVariable("pageNum") int pageNum) {
		PageVO param = new PageVO();
		param.setCategory(category);
		param.setPageNum(pageNum);
		
		ResponseVO response = new ResponseVO();
		response.setRows(pageService.loadPageByCategory(param));
		response.setTotal(pageService.loadPageAll(param));
		
		return ResponseEntity.ok(response);
	}
	
}
