package com.edu.edu_project.entity;

import java.util.ArrayList;
import java.util.List;


public class Page {
	
	private int totalRecord;// ��ʾ��ѯ��һ���õ������������¼
	
    private int pageSize; // ��ʾҳ��һ��Ҫ��ʾ��������¼
    
    private int totalPage;// ��ʾ�����еļ�¼���з�ҳ��һ���ж���ҳ
    
    private int startIndex;// ��ʾ�����еĽ����¼�е���һ����ſ�ʼ��ҳ��ѯ
    
    private int currentPage; // ��ʾ�û��뿴��ҳ��

    @SuppressWarnings("unchecked")
    private List<Book> list = new ArrayList<Book>();// list����������װ��һ��ҳ���е����м�¼��

    public Page(int pageNum, int totalRecord) {
        this.currentPage = pageNum;
        this.totalRecord = totalRecord;

        this.pageSize = 4;// ����һҳĬ����ʾ10����ѯ��¼
        this.startIndex = (this.currentPage - 1) * this.pageSize;// ����Ϊʲôthis.pageҪ��1��
        // ����Ϊmysql���ݿ���ڷ�ҳ��ѯʱ���õ������еĲ�ѯ��¼����һ����¼�ı���Ǵ�0��ʼ��
        if (this.totalRecord % this.pageSize == 0) {
            this.totalPage = this.totalRecord / this.pageSize;
        } else {
            this.totalPage = this.totalRecord / this.pageSize + 1;
        }

    }

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
    

}
