package com.edu.edu_project.entity;

import java.io.Serializable;
import java.util.List;

public class BookEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8789628432724882664L;
	
	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 书名
	 */
	private String title;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 出版社
	 */
	private String publisher;
	
	/**
	 * 封面路径
	 */
	private String coverPath;
	
	/**
	 * 搜索条目
	 */
	private List<String> searchItems;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public List<String> getSearchItems() {
		return searchItems;
	}

	public void setSearchItems(List<String> searchItems) {
		this.searchItems = searchItems;
	}
	
	

}
