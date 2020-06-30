<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chat</title>
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
}

.chat-box {
	width: 400px;
	height: 500px;
	margin: 100px auto;
	border: 1px solid #c9d8de;
	position: relative;
}

.message-area {
	padding: 20px;
	min-height: 400px;
	overflow-y: auto;
	width: 100%;
	height: 80%;
	background:#b2c7d9;
	position:static;
	word-wrap: break-word;
	word-break: break-all;
}

.input-area {
	width: 100%;
	height: 100px;
	overflow: auto;
}

#message-area > div {
margin-bottom: 3px;
}
#message-area > div:last-child{ margin:0;}

 .other_message{
 	border-radius:5px;
 	padding: 3px;

	background:#ffffff;
	display:inline-block;
}
.my_msg_cover{
	text-align:right;
}

.my_message{
	border-radius:5px;
	padding: 3px;
	background:#ffeb33;
	display:inline-block;
} 
</style>
<script>

		function updateScroll() {
			var element = document.getElementById("message-area");
			element.scrollTop = element.scrollHeight;
		}

		$(function() {
			
			var ws = new WebSocket("ws://192.168.60.22/chat/chat");
			 //@ServerEndpoint의 주소를 포함해서 웹 소켓프로토콜(ws://) 주소를 넣어준다.
		     //해당 url에 소켓연결을 요청하면 이 요청을 @serverEndpoint의 클래스가 받게 된다.
			//F5를 통해서 페이지를 리로딩할 때, 기존 접속은 끊어지고 새 아이디를 발급받아서 접속이 이루어진다.

			
			ws.onmessage = function(e){
				var line = $("<div class=other_msg_cover>");
				var line1 = $("<div class=other_message>");
				
				
				line1.append(e.data);
				line.append(line1);
				$(".message-area").append(line);
				updateScroll();
			}
			
			$(".input-area").on("keydown", function(e) {
				if (e.keyCode == 13) {
					
					if($(".input-area").html()=="" || $(".input-area").html()==" "){
						alert("글을 입력하세요.");
					}else {
					var text = $(".input-area").html();
					var line = $("<div class=my_msg_cover>");
					var line1 = $("<div class=my_message>");
					line1.append(text);
					line.append(line1);
					$(".message-area").append(line);
					$(".input-area").empty();
					$(".input-area").focus();
					ws.send(text);
					updateScroll();
					return false;
				}
			}
			});

			

		});
		
		$(function(){
			$("#toBackBtn").on("click",function(){
				location.href="/member/mypage";
			})
		});
	</script>


</head>
<body>
	<div class="chat-box">
		<div id="message-area" class="message-area"></div>
		<div id="input-area" class="input-area" contenteditable="true"></div>
	</div>
	<button id="toBackBtn" class="btn btn-primary btn-lg btn-block text-center" >뒤로 가기</button>
</body>
</html>