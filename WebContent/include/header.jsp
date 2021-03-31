<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="/dog/product/plist">HOME</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/dog/board/boardlist">게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/dog/product/plist">분양하기</a>
      </li>
   <c:choose>
	   <c:when test="${empty sessionScope.user}">  <!--   세션이 없을 때  -->
		    <li class="nav-item">
	        	<a class="nav-link" href="/dog/member/login">로그인</a>
	        </li> 
		    <li class="nav-item">
	       	 <a class="nav-link" href="/dog/member/join">회원가입</a>
	        </li> 
	   </c:when>
	   <c:otherwise>  <!--   세션이 있을 때  -->
	   		<li class="nav-item">
	        	<a class="nav-link" href="/dog/product/pinsert">분양등록</a>
	        </li> 
	   		<li class="nav-item">
	        	<a class="nav-link" href="/dog/member/logout">로그이웃</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link" href="/dog/member/view">회원변경</a>
	        </li> 
	   </c:otherwise>
     </c:choose>  
     </ul>
     <ul class="navbar-nav"> 
    	 <c:if test="${sessionScope.user.admin==1 }">
	     <li class="nav-item">
	        	<a class="nav-link" href="/dog/member/memberlist">회원목록</a>
	     </li>
      	 <span class="navbar-text">(${sessionScope.user.name}(관리자) 님 반갑습니다.)</span>
     	 </c:if>
    	 <c:if test="${sessionScope.user.admin==0 }">
     	 <span class="navbar-text">(${sessionScope.user.name} 님 반갑습니다.)</span>
      </c:if>
     </ul>
  </div>  
</nav>



