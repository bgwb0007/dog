<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<main role="main">
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<div id="carouselExampleControls" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="/dog/upload/main1.png" class="d-block w-100" alt="dog">
						</div>
						<div class="carousel-item">
							<img src="/dog/upload/main2.png" class="d-block w-100" alt="dog">
						</div>
						<div class="carousel-item">
							<img src="/dog/upload/main3.png" class="d-block w-100" alt="dog">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleControls"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleControls"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="card" style="width: 100%; margin-top: 1rem;">
					<div class="card-body">
						<h5 class="card-title">반려견 분양 플랫폼</h5>
						<p class="card-text">새로운 가족을 만들어보세요!</p>
						
					</div>
				</div>
				<div class="card" style="width: 100%; margin-top: 3rem;">
					<div class="card-body">
						<h5 class="card-title">동물 보호 관리시스템</h5>
						<p class="card-text">동물등록제(반려견 등록)<br>여러분의 반려동물 사랑으로 지켜주세요</p>
						<a href="https://www.animal.go.kr/front/index.do"
							class="btn btn-primary">Go web</a>
					</div>
				</div>
				<div class="card" style="width: 100%; margin-top: 3rem;">
					<div class="card-body">
						<h5 class="card-title">전국 종합 유기견 분양센터</h5>
						<p class="card-text">구원의 손길을 기다리는 아이들이 있습니다</p>
						<a href="https://www.zooseyo.or.kr/zooseyo_or_kr.html?"
							class="btn btn-primary">Go web</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<br> <br> <br>

	<div class="container">
		<div class="row">
			<div class="col-12">
				<h4 class="border-bottom shadow-sm">
					<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
						fill="currentColor" class="bi bi-emoji-smile" viewBox="0 0 16 16">
				  <path
							d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
				  <path
							d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z" />
				</svg>
					분양 대기중!
				</h4>
			</div>
			<c:forEach items="${products}" var="product">
				<div class="col-md-6 col-sm-12 col-lg-5 col-xl-4"
					style="margin-top: 1em;">
					<a href="../product/pdetail?productId=${product.id}"><img
						class="card-img-top" src="/dog/upload/${product.filename}"
						alt="Card image" style="width: 100%; height: 300px"></a>
					<div class="card-body">
						<h5 class="card-title text-dark">
							<a href="../product/pdetail?productId=${product.id}"
								class="text-dark"> [${product.category }]${product.name} </a>
						</h5>
						<span>${product.createdDate }</span> <span
							class="badge badge-primary">분양중</span>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<br> <br> <br>

	<div class="container">
		<div class="row">
			<div class="col-12">
				<h4 class="border-bottom shadow-sm">
					<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
						fill="currentColor" class="bi bi-play-btn" viewBox="0 0 16 16">
  <path
							d="M6.79 5.093A.5.5 0 0 0 6 5.5v5a.5.5 0 0 0 .79.407l3.5-2.5a.5.5 0 0 0 0-.814l-3.5-2.5z" />
  <path
							d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm15 0a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z" />
</svg>
					YouTube
				</h4>
				<br>
			</div>
			<div class="col-md-6" style="margin-right: 2rm;">
				<iframe width="100%" height="360"
					src="https://www.youtube.com/embed/6s8H_Irg7-Y"
					title="YouTube video player" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</div>
			<div class="col-md-6">
				<iframe width="100%" height="360"
					src="https://www.youtube.com/embed/eVd77L1Qnco"
					title="YouTube video player" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</div>
		</div>
	</div>

</main>



<%@ include file="../include/footer.jsp"%>
