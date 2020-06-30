<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>modify info</title>
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

.signupBox {
	border: 1px solid black;
}

input {
	width: 75%;
}
</style>
<script>
	$(function() {
		$("#modifyInfoForm").on("submit",function(){
			if($("#pw").val() == ""){
				alert("비밀번호를 입력해주세요!");
				return false;
			}
		});
		
		$("#pw").on("keyup", function() {
			$("#pwck").on("keyup", function() {
				var pw = $("#pw").val();
				var pwck = $("#pwck").val();
				if (pw == pwck) {
					$("#pwCheckDiv").text("비밀번호가 일치합니다.");
					$("#pwCheckDiv").css("color", "blue");
				} else {
					$("#pwCheckDiv").text("비밀번호가 일치하지 않습니다.");
					$("#pwCheckDiv").css("color", "red");
				}
			});
		});
	
		$("#toBackBtn").on("click",function(){
			location.href="mypage";
		})
	});
	
	
</script>

</head>
<body>
	<div class="container">
		<div class="row mt-5 pt-5">
			<div class="col-2"></div>
			<div class="col-8 signupBox  px-1 py-1">
				<div class="row">
					<div class="col-12 text-center h1">내 정보 수정</div>
				</div>

				<form id="modifyInfoForm" action="modifyInfoProc" method="post">
					<div class="row py-1">
						<div class="col-3 text-right">아이디</div>
						<div class="col-5 mx-0 px-0">
							<input type=text name=id id=id value="${loginInfo.id}" readonly>
						</div>
						<div class="col-4" id="idCheckDiv"></div>
					</div>
					<div class="row py-1">
						<div class="col-3 text-right">비밀번호</div>
						<div class="col-5  mx-0 px-0">
							<input type=password name=pw id=pw>
						</div>
						<div class="col-4" id="pwCheckDiv"></div>
					</div>
					<div class="row py-1">
						<div class="col-3 text-right">비밀번호 확인</div>
						<div class="col-5  mx-0 px-0">
							<input type=password name=pwck id=pwck>
						</div>
					</div>

					<div class="row py-1">
						<div class="col-3 text-right">이름</div>
						<div class="col-5  mx-0 px-0">
							<input type=text name=name id=name value= "${loginInfo.name}">
						</div>
					</div>
					<div class="row py-1">
						<div class="col-3 text-right">전화번호</div>
						<div class="col-5  mx-0 px-0">
							<input type=text name=phone id=phone value="${loginInfo.phone}">
						</div>
					</div>
					<div class="row py-1">
						<div class="col-3 text-right">이메일</div>
						<div class="col-7  mx-0 px-0">
							<input type=text name=email id=email value="${loginInfo.email}">
						</div>
					</div>
					<div class="row py-1">
						<div class="col-3 text-right">우편번호</div>
						<div class="col-4  mx-0 px-0">
							<input type=text name=zipcode id=zipcode placeholder="우편번호" value="${loginInfo.zipcode}"
								readonly>
						</div>
						<div class="col-4">
							<button type="button" id="searchZipcodeBtn"
								onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
						</div>
					</div>

					<div class="row py-1">
						<div class="col-3 text-right">주소</div>
						<div class="col-9  mx-0 px-0">
							<input type=text name=address1 id=address1 placeholder="주소" value="${loginInfo.address1}"
								readonly>
								
						</div>
					</div>

					<div class="row py-1">
						<div class="col-3 text-right">상세주소</div>
						<div class="col-9  mx-0 px-0">
							<input type=text name=address2 id=address2 value="${loginInfo.address2}">
						</div>
					</div>

					<script
						src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
					<script>
						function sample6_execDaumPostcode() {
							new daum.Postcode(
									{
										oncomplete : function(data) {
											// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

											// 각 주소의 노출 규칙에 따라 주소를 조합한다.
											// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
											var addr = ''; // 주소 변수
											var extraAddr = ''; // 참고항목 변수

											//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
											if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
												addr = data.roadAddress;
											} else { // 사용자가 지번 주소를 선택했을 경우(J)
												addr = data.jibunAddress;
											}

											// 우편번호와 주소 정보를 해당 필드에 넣는다.
											document.getElementById('zipcode').value = data.zonecode;
											document.getElementById("address1").value = addr;
											// 커서를 상세주소 필드로 이동한다.
											document.getElementById("address2")
													.focus();
										}
									}).open();
						}
					</script>






					<div class="row py-1">
						<div class="col-12 text-center mt-2">
							<button type="submit" class="btn btn-primary" id=signUpBtn>수정</button>
							<button type="button" class="btn btn-light" id=toBackBtn>뒤로가기</button>
						</div>
					</div>
				</form>

			</div>


		</div>

	</div>

</body>
</html>