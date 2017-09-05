<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	    <meta name="google" content="notranslate">
	    <title>Search Result</title>
		<link href="${ctx}/assets/style/search-result.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctx}/assets/style/bootstrap/bootstrap.min.css">
		
		<script src="${ctx}/assets/plugins/jquery-3.2.1.min.js"></script>
		<script src="${ctx}/assets/plugins/jqPaginator.min.js"></script>
		<script src="${ctx}/assets/js/search-result.js"></script>
		
		<script type="text/javascript">
	      var WEB_ROOT = "${ctx}";
	    </script>
	</head>
	<body>
		<div id="main_panel">
			<div class="search-key-div">
				<div class="search-box">${search}</div>
				<div id="total_page" style="display:none">${totalPage}</div>
			</div>
			<div class="tbl-div">
				<table></table>
			</div>
			<div class="">
                <ul id="paginate" class="pagination"></ul>
            </div>
		</div>
		<div id="right_panel" style="display:none">
			<div class="right-panel-top">
				<div class="right-panel-btn"><img onclick="hidePageContent()" alt="" src="${ctx}/assets/img/arrow_carrot_left.png" style="cursor:pointer;"></div>
			</div>
			<div id="right_panel_body" class="right-panel-body">
				<div id="iframe_panel">
					<iframe id="displayPdfIframe" width="100%" height="100%" src="loadPpfJsIframe.htm"></iframe>
				</div>
			</div>
		</div>
	</body>
  <script type="text/javascript">
	  $(document).ready(function() {
		  var totalPage = $("#total_page").text();
		  $("#paginate").jqPaginator({
	            totalPages: parseInt(totalPage),
	            visiblePages: 10,
	            currentPage: 1,
	            first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
	            prev: '<li class="prev"><a href="javascript:void(0);">上一页<\/a><\/li>',
	            next: '<li class="next"><a href="javascript:void(0);">下一页<\/a><\/li>',
	            last: '<li class="last"><a href="javascript:void(0);">尾页<\/a><\/li>',
	            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
	            onPageChange: function (n) {
	            	refreshList(n);
	                $("#demo1-text").html("当前第" + n + "页");
	            }
	        });
	  });
  </script>
</html>