<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Index</title>
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
	box-sizing:border-box;
	font-size: 9pt;
}
    
    .loginbox{
        border:1px solid black;
    }
    
    input{
        width:100%;
    }
    
</style>
</head>
<body>
	<div class="container">
		<div class="row mt-5 pt-5">
		    <div class="col-12 col-sm-4"></div>
			<div class="col-12 col-sm-4 py-1 loginbox">
				<div class="row">
					<div class="col-12 col-sm-12 text-center h1">로그인</div>
        		</div>
        		
        		<form action="/member/loginProc" method="post">
        		<div class="row">
        		    <div class="col-sm-3">ID</div>
        		    <div class="col-12 col-sm-8 mx-0 px-0"><input type=text name=id id=id></div>
        		</div>
        		<div class="row">
        		    <div class="col-sm-3">PW</div>
        		    <div class="col-12 col-sm-8  mx-0 px-0"><input type=password name=pw id=pw></div>
        		</div>
        		
        		<div class="row">
        		    <div class="col-sm-12 text-center mt-2"><button type="submit" class="btn btn-primary btn-lg btn-block" id=loginBtn>로그인</button> <button type="button" class="btn btn-light btn-lg btn-block" id=joinBtn>회원가입</button></div>
        		</div>
        		</form>
        		<script>
        		$(function(){
        			$("#joinBtn").on("click",function(){
        				location.href="/member/signup";
        			});
        		});
        		
        		</script>
			</div>


		</div>

	</div>
	
</body>
</html>