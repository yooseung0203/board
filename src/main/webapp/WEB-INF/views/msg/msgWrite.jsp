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

<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	$(function() {
		$("#idcheck").on("click", function() {
			var id = $("#msg_receiver").val();
			$.ajax({
				url : '../member/idDupleCheck?id=' + id,
				dataType : 'json',
				success : function(resp) {
					console.log(resp);
					if (resp == true) {
						alert("존재하는 회원입니다.");
						$("#msg_receiver").val(id);
						$("#msg_receiver").attr("readonly",true);
					} else {
						alert("존재하지 않는 회원입니다.");
						$("#msg_receiver").val("");
					}
				}
			})
		});
	})
</script>
</head>
<style>
.container {
	border: 1px solid black;
}

.container>div>div {
	border-bottom: 1px solid black;
}
</style>
<body>
	<div class="container" align="center">

		<div class="row">
			<div class="col-12">쪽지보내기</div>
		</div>
		<form action="msgSend" method="post">
			<div class="row" align="center">
				<div class="col-4">닉네임</div>
				<div class="col-8">
					<input type="text" name="msg_receiver" id="msg_receiver">
					<input type="button" id="idcheck" value="확인">
					
				</div>
				<div class="col-4">제목</div>
				<div class="col-8">
					<input type="text" name="msg_title" id="msg_title">
				</div>
				<div class="col-12">내용</div>
				<div class="col-12">
					<input type="text" name="msg_text" id="msg_text">
				</div>
				<div class="col-12">
					<button type="submit" id=signUpBtn>보내기</button>
					<button>취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
