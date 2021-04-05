<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<main role="main">
	<section class="jumbotron text-center">
		<div class="container">
			<h1>Album example</h1>
			<p class="lead text-muted">text text 강아지 입양!</p>
			<p>
				<a href="#" class="btn btn-primary my-2">분양 등록하기</a> <a href="#"
					class="btn btn-secondary my-2">내 등록글 확인하기</a>
			</p>
		</div>
	</section>

	<div class="container">
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
		</form>
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
						<span class="badge badge-primary">분양중</span>
					</div>

				</div>
			</c:forEach>
		</div>
	</div>

</main>



<%@ include file="../include/footer.jsp"%>
