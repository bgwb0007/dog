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
				<td width="200px">이름</td>
				<td>${product.name}</td>
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
				<td colspan="2">
					<button class="btn btn-primary">분양받기</button>
					<button class="btn btn-info">구매하기</button>
				</td>
			</tr>
		</table>
	</div>
</div>

<div class="d-flex container">
	<pre>${product.description}</pre>
</div>

<%@ include file="../include/footer.jsp"%>
