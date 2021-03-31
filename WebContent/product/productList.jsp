<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
 <div class="jumbotron jumbotron-fluid mb-5">
   	<div class="container">
   		<h1>분양리스트</h1>
   	</div>
  </div> 
  <div class="container">
    <div class="row">
	    <c:forEach items="${products}" var="product">
		     <div class="col-4" style="width:400px">
			    <img class="card-img-top" src="/ServletProject03/upload/${product.filename}" alt="Card image" style="width:100%;height:300px">
			    <div class="card-body">
			      <h4 class="card-title">${product.name}</h4>
			      <p class="card-text">${product.description }</p>
			      <a href="pdetail?productId=${product.id}" class="btn btn-primary">상세보기</a>
			    </div>
		 	</div>
	    </c:forEach> 
	    </div>
   </div>
  
  
  


<%@ include file="../include/footer.jsp" %>  