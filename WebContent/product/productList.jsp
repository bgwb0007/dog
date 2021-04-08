<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<main role="main">
	<section class="jumbotron text-center">
		<div class="container">
			<h1>분양 게시글</h1>
			<p class="lead text-muted">반려견 입양으로 새로운 가족을 만들어보세요</p>
			<p>
				<a href="/dog/product/pinsert" class="btn btn-primary my-2">분양
					등록하기</a>
			</p>
		</div>
	</section>

	<div class="container">
		<div class="row">
			<div class="col-sm-12 col-xl-4">
				<form class="form-inline my-2 my-lg-0" method="post">
					<input class="form-control mr-sm-2" type="search" name="keyword" 
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit" id="searchBtn">검색</button>
				</form>
			</div>
			<div class="col-xl-8">
				<a href="plist?status=on" class="btn btn-secondary my-2">분양중</a>
				<a href="plist?status=all" class="btn btn-secondary my-2">전체보기</a>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-md-6 col-sm-12 col-lg-5 col-xl-4"
					style="width: 400px; margin-top: 4em;">
					<a href="pdetail?productId=${product.id}"><img
						class="card-img-top" src="/dog/upload/${product.filename}"
						alt="Card image" style="width: 100%; height: 300px"></a>
					<div class="card-body">
						<h5 class="card-title text-dark">
							<a href="pdetail?productId=${product.id}" class="text-dark">
								[${product.category }]${product.name} </a>
						</h5>
						<span>${product.createdDate }</span>
						<c:choose>
							<c:when test="${product.status =='on'}">
								<span class="badge badge-primary">분양중</span>
							</c:when>
							<c:otherwise>
								<span class="badge badge-secondary">분양완료</span>
							</c:otherwise>
						</c:choose>

					</div>

				</div>
			</c:forEach>
		</div>
	</div>

</main>



<%@ include file="../include/footer.jsp"%>
