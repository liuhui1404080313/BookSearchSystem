<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<!--<meta charset="utf-8">-->
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	    <meta name="google" content="notranslate">
		<link href="${ctx}/assets/style/search-result.css" rel="stylesheet" type="text/css" />
		
		<%-- <link href="${ctx}/assets/style/third_party/viewer.css" rel="stylesheet"/> --%>
		
		<script src="${ctx}/assets/plugins/jquery-3.2.1.min.js"></script>
		
		<script src="${ctx}/assets/js/search-result.js"></script>
		<title>Search Result</title>
		<script type="text/javascript">
	      var WEB_ROOT = "${ctx}";
	    </script>
	</head>
	<body>
	    
		<div id="main_panel" style="width:50%;margin:0% 0% 0% 25%;float:left;">
		<!--<div id="main_panel" style="width:56%;margin-left:3%;float:left"> -->
			<div style="height: 80px;width:100%">
				<div  style="height: 40px;width:90%;border:1px solid #000;margin-top: 3%;border-radius:10px;">${search}</div>
			</div>
			<c:forEach var="book" items="${books}">
				<div style="height: 80px;width:100%;margin-top: 40px;">
					<div class="book-cover" style="background:url('${ctx}${book.coverPath}') no-repeat;cursor:pointer;box-shadow: 2px 2px 5px #888888;" onclick="clickCover(this);"></div>
					<div class="book-info">
						<div class="book-attr">
							<div style="float:left;width:25%;font-size:10px"><span>《${book.title}》</span></div>
							<div style="float:left;width:15%;font-size:10px">${book.author}</div>
							<div style="float:left;width:20%;font-size:10px">${book.publisher}</div>
							<div style="float:right;width:40%">本书含${fn:length(book.searchItems)}条搜索结果，当前是： </div>
						</div>
						<div class="book-cont">
							<div style="float:left;border:1px solid #000;border-radius:15px;margin-right:0px;height: 100%; width:80%">
								<span style="margin:10px">${book.searchItems[0]}</span>
							</div>
							<div style="float:left;height: 100%; width:18%">
								<div style="margin-top:7px;width:70%;height:100%;float:left">
									<div style="width:100%;height:25%;text-align: center"><img onclick="btnDecrease(this,${book.id})" alt="" src="${ctx}/assets/img/arrow_up.png" style="cursor:pointer;"></div>
									<div style="width:100%;height:40%;text-align: center">第<span>1</span>条</div>
									<div style="width:100%;height:25%;text-align: center"><img onclick="btnIncrease(this,${book.id},${fn:length(book.searchItems)})" alt="" src="${ctx}/assets/img/arrow_down.png" style="cursor:pointer;"></div>
								</div>
								<div style="margin-top:3px;width:30%;height:100%;float:left">
									<div style="width:100%;height:33%;text-align: center"></div>
									<img onclick="showPageContent('${book.title}')" alt="" src="${ctx}/assets/img/arrow_right.png" style="cursor:pointer;">
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="right_panel">
			<div class="right-panel-top">
				<div style="width:10%;height:50%;"><img onclick="hidePageContent()" alt="" src="${ctx}/assets/img/arrow_carrot_left.png" style="cursor:pointer;"></div>
				<div class="search d3">
					<form>
					  <input type="text" placeholder="搜索从这里开始...">
					  <button type="submit"></button>
					</form>
				</div>
			</div>
			
			<script type="text/javascript">
				/* $(function(){
			        $("#displayPdfIframe").attr("src",'<c:url value="'+WEB_ROOT+'/plugin/pdfJs/generic/web/viewer.html" />?file=' + WEB_ROOT+'/assets/pdf/LianChengJue.pdf"/>');
			    }); */
			</script>
			<div id="right_panel_body" class="right-panel-body">
				<div id="iframe_panel" style="display: block;height:100%;width:100%">
					<iframe id="displayPdfIframe" width="100%" height="100%" src="loadPpfJsIframe.htm"></iframe>
					<!--  <iframe id="displayPdfIframe" width="100%" height="100%" src="veiwer.jsp"></iframe> -->
<%-- 					<iframe id="displayPdfIframe" width="100%" height="100%" src="${ctx}/assets/plugins/pdfJs/generic/web/viewer.html"></iframe> --%>



				</div>
			</div>
		</div>
	</body>
  <script type="text/javascript">
	  $(document).ready(function() {
		  
		  
		 
		  
		  $(".book-info:first").css("display","block");
		  $("#right_panel").css("display","none");
		  //$("#right_panel").hide();
		  //$(".book-info:first").fadeIn(1500);
		  
		  $("#btn_increase").on("click", function() {
			     var itemNum = $("#item_num").html();
			     $("#item_num").html(parseInt(itemNum) + 1);
			 });
		  $("#arrow_to_right").on("click", function() {
			     $("#main_panel").css("margin-left","3%");
			     $("#main_panel").css("float","left");
			     $("#right_panel").show();
			 });
		  $("#arrow_carrot_left").on("click", function() {
			     $("#right_panel").hide();
			     $("#main_panel").css("margin","0 auto");
			     $("#main_panel").css("float","inherit");
			 });
	  });
	  /* window.onload = function () {
	         var success = new PDFObject({
	             url: WEB_ROOT + "/assets/pdf/LianChengJue.pdf",
	             pdfOpenParams: {
	                 scrollbars: '0',
	                 toolbar: '0',
	                 statusbar: '0'
	                 }
	          }).embed("right_panel_body");
	     }; */
  </script>
</html>