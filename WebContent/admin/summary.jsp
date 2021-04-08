<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>
<script src="../js/member.js"></script>

<div class="container-fluid">
	<div class="row">
		<nav id="sidebarmenu"
			class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
			<div class="sidebar-sticky pt-3">
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link active"
						href="/dog/admin/memberlist">"회원관리"</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="/dog/admin/summary">채팅통계</a></li>
					<li class="nav-item"><a class="nav-link active" href="#">"Dashboard3"</a>
					</li>
					<li class="nav-item"><a class="nav-link active" href="#">"Dashboard4"</a>
					</li>
					<li class="nav-item"><a class="nav-link active" href="#">"Dashboard5"</a>
					</li>
				</ul>
			</div>
		</nav>
		<div class="jumbotron jumbotron-fluid mb-5">
			<div class="container">
				<h1>
					채팅통계
				</h1>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-3 border rounded-pill" style="padding: 2rem;">
					<h4>개설된 채팅방</h4>
					<h5>today : ${chatToday }</h5>
					<h5>Total : ${chatAll }</h5>
				</div>
				<div class="col-3  border rounded-pill" style="padding: 2rem;">
					<h4>입력된 채팅</h4>
					<h5>today : ${messageToday }</h5>
					<h5>Total : ${messageAll }</h5>
				</div>
			</div>
		</div>
	</div>
</div>



<%@ include file="../include/footer.jsp"%>
