<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<script>
function check(){
	if($("#pwd").val()==""){
		alert("비밀번호를 입력하세요");
		return false;
	}
	return true;
}
function delCon(){
	if(confirm("정말 탈퇴할까요?")){
		location.href="delete";
	}
}
</script>
<div class="jumbotron jumbotron-fluid mb-5">
   	<div class="container">
   		<h1>회원변경</h1>
   	</div>
  </div> 
<div class="container">
		
		<form action="update" id="frm" method="post" onsubmit="return check()">
			<div class="form-group">
				<label for="userid">UserID:</label> <input type="text"
						class="form-control" id="userid" value="${member.userid}"
						name="userid" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="name" value="${member.name}" name="name">
			</div>
			
			<div class="form-group">
				<label for="pwd">PWD:</label> <input type="password"
					class="form-control" id="pwd" name="pwd">
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="text"
					class="form-control" id="email" value="${member.email}"
					name="email">
			</div>
			<div class="form-group">
				<label for="phone">Phone</label> <input type="text"
					class="form-control" id="phone" value=${member.phone }
					name="phone">
			</div>
			<div class="form-check-inline">
				<label class="form-check-label"> 
				<input type="radio"	class="form-check-input" name="admin" value="0">일반회원
				</label>
			</div>
			<div class="form-check-inline">
				<label class="form-check-label">
				 <input type="radio" class="form-check-input" name="admin" value="1">관리자
				</label>
			</div>
			<script>
			if(${member.admin==0}){//일반회원
				$("input:radio[value='0']").prop("checked",true);
			}else{//관리자
				$("input:radio[value='1']").prop("checked",true);
			}
			</script>
			<br/>
			<button type="submit" class="btn btn-primary">수정</button>
			<button type="button" class="btn btn-secondary" onclick="delCon()">회원탈퇴</button>
		</form>
	</div>
 <%@ include file="../include/footer.jsp" %>