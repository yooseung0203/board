<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://ssl.pstatic.net/static.gn/js/clickcrD.js"
	id="gnb_clickcrD" charset="utf-8"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12" align="center">받은 쪽지</div>
		</div>
		<div class="row">
			<div class="col-4" >보낸사람</div>
			<div class="col-8">${msgView.msg_sender}</div>
			<div class="col-4">제목</div>
			<div class="col-8"><c:out value="${msgView.msg_title}"></c:out></div>
			<div class="col-12" align="center">내용</div>
			<div class="col-12" align="center"><c:out value="${msgView.msg_text}"></c:out></div>
		</div>
		<div class="row" align="center">
			<div class="col-12" align="center">
				<button id="close">닫기</button>
			</div>
		</div>
	</div>
	<script>
		$("#close").on("click",function(){
			window.close();
		})
	</script>
</body>
</html>
