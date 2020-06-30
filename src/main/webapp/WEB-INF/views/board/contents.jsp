<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Board Contents - ${contents.title} / ${contents.writer}</title>
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

.board_subtitle {
	background-color: #eaebeb;
	vertical-align: middle !important;
}
</style>
<script>
	$(function() {

		$("#toModifyBtn").on("click", function() {
			location.href = "modify?seq=${contents.seq}";
		});

		$("#toDeleteBtn").on("click", function() {
			location.href = "delete?seq=${contents.seq}";
		});

		$("#toBackBtn").on("click", function() {
			location.href = "list";
		})

	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 mt-3 border-bottom border-dark">
				<h2>글보기</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">

				<div class="row border-bottom border-dark">
					<!-- 					<div class="col-2 py-3 bg-primary text-white text-center"><span class="align-middle font-weight-bolder h3">제목</span></div> -->
					<div
						class="col-sm-12  bg-dark text-white py-3 text-center align-middle">
						<span class="align-middle font-weight-bolder h3"><c:out
								value="${contents.title}" /></span>
					</div>
				</div>
				<div class="row border-bottom border-dark">
					<div
						class="col-sm-2 board_subtitle  font-weight-bolder  text-center">글번호</div>
					<div class="col-sm-10">${contents.seq}</div>
				</div>
				<div class="row border-bottom border-dark">
					<div
						class="col-sm-2 board_subtitle  font-weight-bolder  text-center">작성자</div>
					<div class="col-sm-10">${contents.writer}(${contents.ip_addr})</div>
				</div>
				<div class="row border-bottom border-dark">
					<div
						class="col-sm-2 board_subtitle  font-weight-bolder  text-center">작성일</div>
					<div class="col-sm-10">${contents.sDate}</div>
				</div>
				<div class="row border-bottom border-dark">
					<div
						class="col-sm-2 board_subtitle font-weight-bolder  text-center">조회수</div>
					<div class="col-sm-10">${contents.view_count}</div>
				</div>
				<div class="row border-bottom border-dark">
					<div
						class="col-sm-2 board_subtitle font-weight-bolder align-middle text-center py-3">내용</div>
					<div class="col-sm-10 align-middle py-3">${contents.contents}</div>
				</div>
				<div class="row border-bottom border-dark">
					<div
						class="col-sm-2 board_subtitle font-weight-bolder  text-center">첨부파일</div>
					<div class="col-sm-10">
						<c:if test="${!empty fileList }">
							<ul>
								<c:forEach var="i" items="${fileList }">
									<li><a href='/file/downloadFile?seq=${i.seq}'>${i.oriName}</a></li>
								</c:forEach>
							</ul>
						</c:if>

					</div>
				</div>
				<!-- 댓글 리스트 -->
				
				<c:if test="${empty replyList }">
				<div class="row border-bottom border-dark">
					<div class="col-sm-12 align-middle py-3">
						<div class="row">
							<div class="col-sm-12">댓글이 없습니다.</div>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${!empty replyList }">
				<c:forEach var="j" items="${replyList}">				
				<div class="row border-bottom border-dark">
					<div class="col-sm-12 align-middle py-3">
						<div class="row">
							<div class="col-sm-1"><b>${j.writer }</b></div>
							<div class="col-sm-1">${j.sDate }</div>
						</div>
						<div class="row">
							<div class="col-sm-12">${j.contents }</div>
						</div>
					</div>
				</div>
				</c:forEach>
				</c:if>
				
				
				
				<!-- 댓글 작성 -->
				<form id="replyFrom" action="/comment/replyProc" method="post">
				<div class="row border-bottom border-dark">
					<div class="col-sm-10 align-middle form-group py-3">
						<input type="hidden" id="cpq" name="parent_seq" value="${contents.seq}">
						<input type="hidden" id="cw" name="writer" value="${sessionScope.loginInfo.id}">
						
						<script>
						var parent_seq = $("#cpq").val();
						var writer = $("#cw").val();
						console.log(parent_seq);
						console.log(writer);
						</script>
												<textarea class="form-control" rows="3" id=replyInput
							name="contents"></textarea>
					</div>
						<div class="col-sm-2 text-center align-middle" >
						<button type="submit" id="replySummit" class="btn btn-primary btn-lg mt-3" style="vertical-align: middle; height:60px;" >댓글달기</button>
						</div>
				</div>
				</form>
			</div>
		</div>
		<div class="row mt-5">
			<div class="col-12 mb-3 col-sm-4">
				<button class="btn btn-light btn-lg btn-block" id="toBackBtn">뒤로가기</button>
			</div>
			<c:if test="${loginInfo.id eq contents.writer}">
				<div class="col-12 mb-3  col-sm-4">
					<button class="btn btn-warning btn-lg btn-block" id="toModifyBtn">수정하기</button>
				</div>
				<div class="col-12 mb-3  col-sm-4">
					<button class="btn btn-danger btn-lg btn-block" id="toDeleteBtn">삭제하기</button>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>