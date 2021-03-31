<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
<script src="../js/member.js"></script> 
  <div class="jumbotron jumbotron-fluid mb-5">
   	<div class="container">
   		<h1>회원리스트(<span id="cntSpan">${count }</span>)</h1>
   	</div>
  </div>   
<div class="container">
  
  <table class="table table-hover">
    <thead>
      <tr>
        <th>이름</th>
        <th>아이디</th>
        <th>전화번호</th>
        <th>이메일</th>
        <th>구분</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${members}" var="mem">
   	  <tr>
        <td>${mem.name}</td>
        <td>${mem.userid}</td>
        <td>${mem.phone}</td>
        <td>${mem.email}</td>
        
        <c:if test="${mem.admin==0}">
	        <td>일반회원</td>
	        <td onclick="del('${mem.userid}')">삭제</td>
        </c:if>
        <c:if test="${mem.admin==1}">
	        <td>관리자</td>
	        <td>Admin</td>
        </c:if>
       
      </tr>
    
    </c:forEach>
   </tbody>
  </table>
</div>  
 <%@ include file="../include/footer.jsp" %>  