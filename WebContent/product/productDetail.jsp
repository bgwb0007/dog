<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid mb-5">
	<div class="container">
		<h1>상세보기</h1>
	</div>
</div>
<div class="d-flex container">
	<div class="card" style="width: 400px">
		<img class="card-img-top" src="/dog/upload/${product.filename}"
			alt="Card image">
	</div>
	<div class="container">
		<table class="table table-borderless">
			<tr>
				<td width="200px">등록인</td>
				<td>${product.userid }</td>
			</tr>
			<tr>
				<td width="200px">이름</td>
				<td>${product.name }</td>
			</tr>
			<tr>
				<td>견종</td>
				<td>${product.category }</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>${product.age}개월</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>${product.gender}</td>
			</tr>

			<tr>
				<td colspan="2"><c:if
						test="${sessionScope.user.userid==product.userid }">
						<a href="#" class="btn btn-secondary my-2">수정</a>
						<a href="#" class="btn btn-secondary my-2">삭제</a>
						<a href="/dog/product/salecompleted?productId=${product.id }" class="btn btn-secondary my-2">분양 완료</a>
					</c:if> <c:if test="${sessionScope.user.userid!=product.userid }">
						<a href="#" class="btn btn-primary my-2">분양 신청</a>
					</c:if></td>
			</tr>
		</table>
	</div>
</div>

<div class="container">
	<pre>${product.description}</pre>
	<br> 
	<img src="/dog/upload/${product.filename}" class="img-fluid"
		alt="Responsive image">

</div>

<%@ include file="../include/footer.jsp"%>
