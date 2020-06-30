<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("#list_admin").on("click", function() {
			location.href = "msg_list_admin";
		})
		$("#list_sender").on("click", function() {
			location.href = "msg_list_sender";
		})
		$("#list_receiver").on("click", function() {
			location.href = "msg_list_receiver";
		})
	})
</script>

<title>Insert title here</title>
</head>
<style>
.container>div {
	border: 1px solid black;
}
</style>
<body>
	<div class="container" align="center">
		<div class="row">
			<div class="col-12">쪽지함</div>
		</div>
		<div class="row">
			<div class="col-12" align="center">
				<input type="button" value="관리자" id="list_admin"> <input
					type="button" value="받은 쪽지함" id="list_sender"> <input
					type="button" value="보낸 쪽지함" id="list_receiver">
			</div>
		</div>
		<div class="row" align="center">
			<div class="col-2">읽음</div>
			<div class="col-2">보낸사람</div>
			<div class="col-4">제목</div>
			<div class="col-2">날짜</div>
			<div class="col-2">삭제</div>
		<c:if test="${empty list}">
			<div class="col-12" align>쪽지가 없습니다.</div>
		</c:if>
		
			<c:forEach var="i" items="${list}" varStatus="status">
				<div class="col-2">${i.msg_seq}</div>
				<div class="col-2">${i.msg_sender}</div>
				<div class="col-4">${i.msg_title}</div>
				<div class="col-2">${i.msg_date}</div>
				<div class="col-2">
					<button>삭제</button>
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="col-12" align="center">
				<button id="writeMsg">쪽지보내기</button>
			</div>
		</div>
	</div>
	<script>
		$("#writeMsg").on("click", function() {
			location.href = "msgWrite";
		})
	</script>
</body>
</html>