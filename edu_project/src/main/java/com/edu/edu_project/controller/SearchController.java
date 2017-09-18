package com.edu.edu_project.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

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
import org.springframework.web.servlet.ModelAndView;

import com.edu.edu_project.entity.Book;
import com.edu.edu_project.entity.BookEntity;
import com.edu.edu_project.entity.Page;

@Controller
public class SearchController {
	/*
	 * ����spring�ṩ���ϴ��ļ��ķ���
	 */
	@RequestMapping("/springUpload.htm")
	public String springUpload(HttpServletRequest request)
			throws IllegalStateException, IOException {
		System.out.println("enter controller");
		long startTime = System.currentTimeMillis();
		// ����ǰ�����ĳ�ʼ���� CommonsMutipartResolver ���ಿ�ֽ�������
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// ���form���Ƿ���enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// ��request��ɶಿ��request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// ��ȡmultiRequest �����е��ļ���
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// һ�α��������ļ�
				MultipartFile file = multiRequest.getFile(iter.next()
						.toString());
				if (file != null) {
					String pathString = request.getSession()
							.getServletContext().getRealPath("");
					System.out.println(pathString);
					String path = pathString + File.separator
							+ file.getOriginalFilename();
					System.out.println("path===" + path);
					// �ϴ�
					file.transferTo(new File(path));
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("������������ʱ�䣺" + String.valueOf(endTime - startTime)
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

	@RequestMapping(value = "/login.htm")//��ѡ��ҳ��
	public String login() {
		return "jsp/optionPage.jsp";
	}
	@RequestMapping(value = "/toSearch.htm")//��ѡ��ҳ��
	public String searchResult1() {
		
		return "jsp/searchOrUpload.jsp";
	}

	/**
	 * ��ת���������ҳ��
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toSearchResultPage.htm", produces = {
			"text/html;charset=UTF-8;", "application/json;" })
	public String toSearchResultPage(String search, ModelMap model) {
		model.addAttribute("search", search);
		Page page = getPage(1);
		model.addAttribute("totalPage", page.getTotalPage());
		
		return "jsp/searchResult.jsp";
	}
	
	/**
	 * ˢ���б�
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(value = "/refreshList.htm", method = RequestMethod.GET, produces = {
			"text/html;charset=UTF-8;", "application/json;" })
	@ResponseBody
	public String refreshList(int currentPage)
	{
		Page page = getPage(currentPage);
		return JSONArray.fromObject(page).toString();
	}
	
	private Page getPage(int currentPage)
	{
		List<Book> books = getBooks();
		Page page = new Page(currentPage,books.size());
		
		List<Book> onepageBooks = new ArrayList<Book>();
		for (int i=0;i<books.size();i++)
		{
			if (i>=page.getStartIndex() && i<page.getStartIndex()+page.getPageSize())
			{
				onepageBooks.add(books.get(i));
			}
		}
		
		page.setList(onepageBooks);
		return page;
	}
	
	/**
	 * ����pdf
	 * @param file
	 * @return
	 */
	@RequestMapping (value = "/loadPpfJsIframe.htm")
	public ModelAndView loadPpfJsIframe(@ModelAttribute("file")String file)
	{
		ModelAndView model = new ModelAndView("jsp/viewer.jsp");
		model.addObject("file", "assets/pdf/"+file+".pdf");
		return model;
	}

	/**
	 * ����book ID��ѯ������Ŀ
	 * @param bookId book ID
	 * @param itemNum ��itemNum��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/searchContByIdAndItem.htm", method = RequestMethod.GET, produces = {
			"text/html;charset=UTF-8;", "application/json;" })
	@ResponseBody
	public String searchContByIdAndItem(@ModelAttribute("bookId") int bookId,
			@ModelAttribute("itemNum") int itemNum, Model model) {
		String item = "";
		List<Book> books = getBooks();
		for (Book book : books) {
			if (book.getBookId() == bookId) {
				item = book.getContent().get(itemNum);
			}
		}
		return item;
	}
	
	// ================================����Ϊ����������ݷ���==========================================
	private List<Book> getBooks() {
		
		List<Book> bookList = new ArrayList<Book>();
		for (int i=0;i<50;i++)
		{
			Book book = new Book();
			book.setBookId(i);
			if (i % 2 == 0)
			{
				book.setBookName("LianChengJue");
			} else {
				book.setBookName("compressed");
			}
			book.setAuthor("Joshua Bloch");
			book.setCover("/assets/bookCovers/Effective java.png");
			book.setPublisher("��е��ҵ������");
			List<String> item = new ArrayList<String>();
			item.add("�������1");
			item.add("�������2");
			book.setContent(item);
			bookList.add(book);
		}
		//Page page = new Page(1,1);

		List<BookEntity> books = new ArrayList<BookEntity>();
		for (int i=0;i<4;i++)
		{
			BookEntity b1 = new BookEntity();
			b1.setId(1);
			b1.setTitle("Effective java"+i);
			b1.setCoverPath("/assets/bookCovers/Effective java.png");
			b1.setAuthor("Joshua Bloch");
			b1.setPublisher("��е��ҵ������");
			List<String> items1 = new ArrayList<String>();
			items1.add("������Ϣ1");
			items1.add("������Ϣ2");
			b1.setSearchItems(items1);
			books.add(b1);
		}
		return bookList;
	}

}
