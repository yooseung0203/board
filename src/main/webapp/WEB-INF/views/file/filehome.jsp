<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
$(function(){
	$("#fileAdd").on("click",function(){
		var fileComp = $("<div><input type='file' name='files'></div>");
		$("#fileAdd").after(fileComp);
	})
})

</script>
</head>
<body>
	<form action="filesUpload"  method="post" enctype="multipart/form-data">
		<input type=text name=writer placeholder="input your name"><br>
		<input type=text name=contents placeholder="input message"><br>
		<button type=button id="fileAdd">파일첨부[+]</button> <input type="submit">
		
	</form>
	
	
	
<!-- 	<form action="filesUpload"  method="post" enctype="multipart/form-data">
		<input type=file name=files><br> 
		<input type=file name=files><br>
		<input type=file name=files><br>
		<input type=file name=files><br>
		<input type=file name=files><br>
		<input type="submit">
	</form> -->
</body>
</html>