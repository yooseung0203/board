<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>







<style>
* {
	box-sizing: border-box;
	font-size: 9pt;
}
</style>
<script>
	$(function() {
		$("#fileAdd").on("click", function() {
			var fileComp = $("<div><input type=file name=files></div>");
			$("#fileAdd").after(fileComp);
		})
	});
	$(function() {
		$("#toBackBtn").on("click", function() {
			location.href = "list";
		});
		$("#form").on("submit", function() {
			$("#contents").val($('#summernote').summernote('code'));
			console.log($("#contents").val());
			if ($("#title").val() == "" || $("#contents").val() == "") {
				alert("글쓰기 내용을 입력해주세요!");
				return false;
			}

			var title_RegEx = /[a-zA-Z0-9]{5,}$/;
			var title = $("#title").val();
			var contents = $("#contents").val();
			if (!write_RegEx.test(title)) {
				alert("제목은 5글자 이상 입력해주세요.");
				return false;
			}
			/* 
			 if (!write_RegEx.test(contents)) {
			 alert("내용은 5글자 이상 입력해주세요.");
			 return false;
			 }
			 */
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12 text-center mt-5">
				<h2>글수정</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<form id="form" action="modifyProc" method="post">
					<input type="hidden" name="seq" value="${contents.seq}">
					<div class="row my-2">
						<div class="col-2 text-center">제목</div>
						<div class="col-10">
							<input type="text" class="form-control" name="title" id="title"
								value="${contents.title}">
						</div>

					</div>
					<div class="row my-2">
						<div class="col-2 text-center">내용</div>
						<div class="col-10">
							<div id="summernote"></div>
							<script>
								$('#summernote')
										.summernote(
												{
													minHeight : 500, // 최소 높이
													maxHeight : null, // 최대 높이
													focus : true, // 에디터 로딩후 포커스를 맞출지 여부
													//  lang: "ko-KR",					// 한글 설정
													spellCheck : false,
													callbacks : { //이미지 첨부하는 부분
														onImageUpload : function(
																files) {
															uploadSummernoteImageFile(
																	files[0],
																	this);
														}
													}
												});
								var con = '${contents.contents}';
								$('#summernote').summernote('pasteHTML', con);

								function uploadSummernoteImageFile(file, editor) {
									data = new FormData();
									data.append("file", file);
									$.ajax({
										data : data,
										type : "POST",
										url : "/uploadSummernoteImageFile",
										contentType : false,
										processData : false,
										success : function(data) {
											//항상 업로드된 파일의 url이 있어야 한다.
											console.log(data)
											$(editor).summernote('insertImage',
													"/resources" + data.url);
										}
									});
								};
							</script>
							<textarea id="contents" name="contents" style="display: none;"></textarea>

						</div>
					</div>
					<div class="row my-2">
						<div class="col-2 text-center">첨부파일</div>
						<div class="col-10">
							<button type=button id="fileAdd">파일첨부[+]</button>
						</div>

					</div>
					<div class="row">
						<div class="col-12 text-center">
							<button type="button" id="toBackBtn" class="btn btn-light">목록으로</button>
							<button type="submit" id="submit" class="btn btn-warning">작성하기</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
</body>
</html>