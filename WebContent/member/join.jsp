<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="../js/member.js"></script>
<div class="jumbotron jumbotron-fluid mb-5">
	<div class="container">
		<h1>회원가입</h1>
	</div>
</div>
<div class="container">

	<form action="join" id="frm" method="post">
		<div class="form-group">
			<label for="name">Name:</label> <input type="text"
				class="form-control" id="name" placeholder="Enter name" name="name">
		</div>
		<div class="row">
			<div class="col">
				<label for="userid">UserID:</label> <input type="text"
					class="form-control" id="userid" placeholder="Enter UserId"
					name="userid" readonly="readonly">
			</div>
			<div class="col  align-self-end">
				<button type="button" id="idCheckBtn" class="btn btn-primary">중복확인</button>
			</div>
		</div>
		<div class="form-group">
			<label for="pwd">PWD:</label> <input type="password"
				class="form-control" id="pwd" placeholder="Enter pwd" name="pwd">
		</div>
		<div class="form-group">
			<label for="pwd_check">PWD_Check:</label> <input type="password"
				class="form-control" id="pwd_check" placeholder="Enter pwd_check"
				name="pwd_check">
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="text"
				class="form-control" id="email" placeholder="Enter Email"
				name="email">
		</div>
		<div class="form-group">
			<label for="phone">Phone</label> <input type="text"
				class="form-control" id="phone" placeholder="Enter Phone"
				name="phone">
		</div>
		<input type="hidden" name="admin" value="0"> <br />
		<button type="button" class="btn btn-primary" id="sendBtn">Submit</button>
	</form>
</div>
<%@ include file="../include/footer.jsp"%>