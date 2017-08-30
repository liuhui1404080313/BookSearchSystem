package com.edu.edu_project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.edu.edu_project.entity.BookEntity;

@Controller
public class SearchController {
	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping("/springUpload.htm")
	public String springUpload(HttpServletRequest request)
			throws IllegalStateException, IOException {
		System.out.println("enter controller");
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next()
						.toString());
				if (file != null) {
					String pathString = request.getSession()
							.getServletContext().getRealPath("");
					System.out.println(pathString);
					String path = pathString + File.separator
							+ file.getOriginalFilename();
					System.out.println("path===" + path);
					// 上传
					file.transferTo(new File(path));
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime)
				+ "ms");
		return "jsp/page1.jsp";
	}

	@RequestMapping(value = "/upload.htm")
	public String upload() {
		return "jsp/bookUpload.jsp";
	}
	@RequestMapping(value = "/searchResult.htm")
	public String searchResult() {
		return "jsp/searchResult.jsp";
	}

	@RequestMapping(value = "/login.htm")//到选择页面
	public String login() {
		return "jsp/page1.jsp";
	}
	@RequestMapping(value = "/toSearch.htm")//到选择页面
	public String searchResult1() {
		
		return "jsp/searchOrUpload.jsp";
	}

	@RequestMapping(value = "/search.htm", produces = {
			"text/html;charset=UTF-8;", "application/json;" })
	public String search(String search, ModelMap model) {
		model.addAttribute("search", search);
		model.addAttribute("books", getBooks());
		System.out.println("enter search.htm");
		return "jsp/searchResult.jsp";
	}
	
	@RequestMapping (value = "/loadPpfJsIframe.htm")
	public String loadPpfJsIframe(String file)
	{
		return "jsp/viewer.jsp?file=assets/pdf/" + file + ".pdf";
//		return "jsp/viewer.jsp" + "?file=assets/pdf/LianChengJue.pdf";
//		return "plugins/pdfJs/generic/web/viewer.html";
//		return "login";
	}

	@RequestMapping(value = "/searchContByIdAndItem.htm", method = RequestMethod.GET, produces = {
			"text/html;charset=UTF-8;", "application/json;" })
	@ResponseBody
	public String searchContByIdAndItem(@ModelAttribute("bookId") int bookId,
			@ModelAttribute("itemNum") int itemNum, Model model) {
		String item = "";
		List<BookEntity> books = getBooks();
		for (BookEntity book : books) {
			if (book.getId() == bookId) {
				item = book.getSearchItems().get(itemNum);
			}
		}
		return item;
	}

	private List<BookEntity> getBooks() {
		BookEntity b1 = new BookEntity();
		b1.setId(1);
		b1.setTitle("LianChengJue");
		b1.setCoverPath("/assets/img/songshu.png");
		b1.setAuthor("Benjamin Way");
		b1.setPublisher("中信出版社");
		List<String> items1 = new ArrayList<String>();
		items1.add("搜索信息1");
		items1.add("搜索信息2");
		b1.setSearchItems(items1);

		BookEntity b2 = new BookEntity();
		b2.setId(2);
		b2.setTitle("compressed");
		b2.setCoverPath("/assets/bookCovers/Effective java.png");
		b2.setAuthor("Joshua Bloch");
		b2.setPublisher("机械工业出版社");
		List<String> items2 = new ArrayList<String>();
		items2.add("第三个");
		items2.add("二个");
		b2.setSearchItems(items2);

		List<BookEntity> books = new ArrayList<BookEntity>();
		books.add(b1);
		books.add(b2);
		return books;
	}

}
