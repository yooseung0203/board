<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Board List</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
* {
	box-sizing: border-box;
	font-size: 9pt;
}
</style>
<script>
$(function(){

	$("#toWriteBtn").on("click",function(){
		location.href="write";
	});
	
	$("#toBackBtn").on("click",function(){
		location.href="/member/mypage";
	})
	
});

</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12 mt-3">
				<h2>글목록</h2>
			</div>
		</div>
		<div class="row">
			<div class="table-responsive">
				<table class="table border-bottom border-dark">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">글제목</th>
							<th scope="col">작성자</th>
							<th scope="col">작성일</th>
							<th scope="col">조회수</th>
							<th scope="col">IP주소</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" items="${list}" varStatus="status">
						<tr>
							<th scope="row">${i.seq}</th>
							<td><a href="contents?seq=${i.seq}"><c:out value="${i.title}"/></a></td>
							<td>${i.writer}</td>
							<td>${i.sDate}</td>
							<td>${i.view_count}</td>
							<td>${i.ip_addr}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-2">
				<button class="btn btn-light" id="toBackBtn">뒤로가기</button>
			</div>
			<div class="col-8">
				${navi}
			</div>
			<div class="col-2">
				<button class="btn btn-primary" id="toWriteBtn">글쓰기</button>
			</div>
		</div>
	</div>
</body>
</html>