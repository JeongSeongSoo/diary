package org.diary.web.page.controller;

import org.diary.web.page.model.PageVO;
import org.diary.web.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/page")
@Controller
public class PageController {

	@Autowired
	private PageService pageService;
	
	@GetMapping({"/daily", "/{pageNum}/daily"})
	public String daily(@PathVariable(value = "pageNum", required = false) String pageNum, Model model) {
		if (pageNum == null) pageNum = "1";
		model.addAttribute("pageNum", pageNum);
		return "daily";
	}
	
	@GetMapping("/{pageId}/update")
	public String update(@PathVariable("pageId") int pageId, Model model) {
		PageVO page = pageService.loadPageByPageId(pageId);
		
		model.addAttribute("page", page);
		
		return "update";
	}
	
	@GetMapping("/{pageId}/delete")
	public String delete(@PathVariable("pageId") int pageId) {
		pageService.removePageByPageId(pageId);
		return "redirect:/page/daily";
	}
	
	@GetMapping("/category/{category}/write")
	public String write(Model model, @PathVariable("category") String category) {
		model.addAttribute("category", category);
		return "write";
	}
	
	@GetMapping("/pageId/{pageId}/load")
	public String loadPageByPageId(@PathVariable("pageId") int pageId, Model model) {
		PageVO page = pageService.loadPageByPageId(pageId);
		model.addAttribute("page", page);
		return "detail";
	}
	
	@PostMapping("/add")
	public String addPage(PageVO param) {
		pageService.addPage(param);
		return "redirect:/page/daily";
	}
	
	@PostMapping("/modify")
	public String modify(PageVO param) {
		pageService.modifyPage(param);
		return "redirect:/page/daily";
	}
	
}
