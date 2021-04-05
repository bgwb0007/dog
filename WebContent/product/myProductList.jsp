<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<main role="main">
<section class="jumbotron text-center">
	<div class="container">
		<h1>Album example</h1>
		<p class="lead text-muted">text text 강아지 입양!</p>
		<p>
			<a href="#" class="btn btn-primary my-2">분양 등록하기</a>
			<a href="#" class="btn btn-secondary my-2">내 등록글 확인하기</a>
		</p>
	</div>
</section>

  <div class="container">
    <div class="row">
	    <c:forEach items="${products}" var="product">
		     <div class="col-md-6 col-sm-12 col-lg-5 col-xl-4" style="width:400px">
			    <img class="card-img-top" src="/dog/upload/${product.filename}" alt="Card image" style="width:100%;height:300px">
			    <div class="card-body">
			      <h4 class="card-title">${product.name}</h4>
			      <p class="card-text">${product.category }</p>
			      <a href="pdetail?productId=${product.id}" class="btn btn-primary">상세보기</a>
			    </div>
		 	</div>
	    </c:forEach> 
	    </div>
   </div>
  
</main>


  


<%@ include file="../include/footer.jsp" %>  