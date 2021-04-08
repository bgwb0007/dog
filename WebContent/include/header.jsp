<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
		<h5 class ="my-0 mr-md-auto font-weight-normal"><a href="/dog/index/main" class="text-dark">HOME</a></h5>
		<nav class="my-2 my-md-0 mr-md-3">
			<a class="p-2 text-dark" href="/dog/product/plist">분양하기</a>
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<!--   세션이 없을 때  -->
					<a class="p-2 text-dark" href="/dog/member/login">로그인</a>
					<a class="p-2 text-dark" href="/dog/member/join">회원가입</a>
				</c:when>
				<c:otherwise>
					<!--   세션이 있을 때  -->
					<a class="p-2 text-dark" href="/dog/product/pinsert">분양등록</a>
					<a class="p-2 text-dark" href="/dog/chat/list">채팅목록</a>
					<a class="p-2 text-dark" href="/dog/member/logout">로그아웃</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${sessionScope.user.admin==1 }">
				<a class="p-2 text-dark" href="/dog/admin/dashboard">관리페이지</a>
				<span class="navbar-text">(<a class="p-2 text-dark" href="/dog/member/view">${sessionScope.user.name}(관리자)</a> 님
					반갑습니다.)</span>
			</c:if>
			<c:if test="${sessionScope.user.admin==0 }">
				<span class="navbar-text">(<a class="p-2 text-dark" href="/dog/member/view">${sessionScope.user.name}</a> 님
					반갑습니다.)</span>
			</c:if>
		</nav>
	</div>