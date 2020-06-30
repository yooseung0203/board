<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>My page</title>
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
		$("#toChatBtn").on("click", function() {
			location.href = "/chat/chat";
		});
		
		$("#toBoardBtn").on("click", function() {
			location.href = "/board/list?cpage=1";
		});

		$("#toModifyBtn").on("click", function() {
			location.href = "modifyinfo";
		})

		$("#toLogoutBtn").on("click", function() {
			location.href = "logout";
		})
		$("#toMsgBtn").on("click",function(){
			location.href = "/msg/msg_list_sender";
		})

		$("#toMemberoutBtn").on("click", function() {
			var con = confirm("정말 탈퇴하겠습니까?");
			if (con == true) {
				location.href = "memberout";
			}
		})

	})
</script>
<style>
* {
	box-sizing: border-box;
	font-size: 9pt;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-12 col-sm-12 text-center">
				<span class="font-weight-bold">${loginInfo.id} </span>님 환영합니다.<br>
				<br>
			</div>
		</div>
		<div class="row">
			<div class="col-12 col-sm-3 mb-2">
				<button type="button" class="btn btn-primary btn-lg btn-block"
					id="toChatBtn">채팅시작</button>
			</div>
			<div class="col-12 col-sm-3 mb-2">
				<button type="button" class="btn btn-primary btn-lg btn-block" id="toMsgBtn">
					쪽지함
				</button>
			</div>
			<div class="col-12 col-sm-3 mb-2">
				<!-- <button type="button" class="btn btn-warning btn-lg btn-block"
					id="toLogoutBtn">로그아웃</button> -->
			</div>
			<div class="col-12 col-sm-3 mb-2">
				<!-- <button type="button" class="btn btn-danger btn-lg btn-block"
					id="toMemberoutBtn">회원탈퇴</button> -->
			</div>

		</div>
		
		<div class="row">
			<div class="col-12 col-sm-3 mb-2">
				<button type="button" class="btn btn-primary btn-lg btn-block"
					id="toBoardBtn">게시판이동</button>
			</div>
			<div class="col-12 col-sm-3 mb-2">
				<button type="button" class="btn btn-secondary btn-lg btn-block"
					id="toModifyBtn">내정보수정</button>
			</div>
			<div class="col-12 col-sm-3 mb-2">
				<button type="button" class="btn btn-warning btn-lg btn-block"
					id="toLogoutBtn">로그아웃</button>
			</div>
			<div class="col-12 col-sm-3 mb-2">
				<button type="button" class="btn btn-danger btn-lg btn-block"
					id="toMemberoutBtn">회원탈퇴</button>
			</div>

		</div>
		
		
		<div class="row">
		<div class="col-12 col-sm-4"></div>
		<div class="col-12 col-sm-4">
			<img src="/resources/img/EYrL_GTU0AIcRZp.jpg" class="rounded img-fluid mx-auto">
		</div>
		</div>
	</div>
</body>
</html>