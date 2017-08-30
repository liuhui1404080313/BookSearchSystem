<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<html>
<head>
<title>搜书系统前端展示</title>
<link href="${ctx}/assets/style/search.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<div id="container" align="center" style="margin-top:15%">
		<form id="search_box" style="width:50%" name="searchForm" action="search.htm" method="post">
			<div class="wrapper">
				<input type="text" id="search" name="search"
					placeholder="Search for books" />
				<button id = "search_button" type="submit" class="search_btn">
					<img src="${ctx}/assets/img/search.ico" title="Search" />
				</button>
			</div>
		</form>

	</div>
	<form action="upload.htm" method="post" name="bookUploadForm">
		<div>
			<button id = "book_upload" type="submit">
				书本上传
			</button>
		</div>	
	</form>
	<script src="${ctx}/assets/plugins/jquery-3.2.1.min.js"></script>
	<script>
	$('#book_upload').click(function (event) {
		/* var keyWord = $('#search').val(); */
		/* alert(keyWord); */
		event.preventDefault();
		bookUpload();
	});
	function bookUpload(){
		if(document.bookUploadForm.onsubmit &&
	   	    !document.bookUploadForm.onsubmit()) //判断是否正在提交，如果是则直接返回
	   	    {
	   	        return;
	   	    }
	   	 document.bookUploadForm.submit(); //调用form的submit()方法完成表单的提交
	}
	$('#search_button').click(function (event) {
		/* var keyWord = $('#search').val(); */
		/* alert(keyWord); */
		event.preventDefault();
		searchForm();
	});
	function searchForm()
	{
	    if(document.searchForm.onsubmit &&
    	    !document.searchForm.onsubmit()) //判断是否正在提交，如果是则直接返回
    	    {
    	        return;
    	    }
    	 document.searchForm.submit(); //调用form的submit()方法完成表单的提交
	}
	</script>
</body>
</html>