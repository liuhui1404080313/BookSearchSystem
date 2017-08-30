<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#uploadForm{
	margin-left:40%;
	margin-top:15%;
    width:22%;
    height:50px;
}
#fileinput{
	margin-left:0;
	width:100%;
	height:40px;
}
#book_upload{
	margin-left:0;
	width:100%;
	height:100%;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>PDF file upload</title>
</head>
<body>
	<form name="bookUploadForm" action="springUpload.htm" method="post"
		enctype="multipart/form-data" id="uploadForm" >
		<h2>请上传需要搜索的PDF文件</h2>
		<input class="file" id="fileinput" type="file" name="file" /> 
		<input type="submit" value="upload"	id=book_upload />
	</form>
	<script type="text/javascript">
		$('#book_upload').click(function(event) {
			event.preventDefault();
			bookUpload();
		}); 
		function bookUpload() {
			if (document.bookUploadForm.onsubmit
					&& !document.bookUploadForm.onsubmit()) //判断是否正在提交，如果是则直接返回
			{
				return;
			}
			document.bookUploadForm.submit(); //调用form的submit()方法完成表单的提交
		} 
	</script>
</body>
</html>