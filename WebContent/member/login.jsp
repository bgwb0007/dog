<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

 <div class="jumbotron jumbotron-fluid mb-5">
   	<div class="container">
   		<h1>로그인</h1>
   	</div>
  </div> 
<div class="container">
  

    <div class="form-group">
      <label for="userid">Userid:</label>
      <input type="text" class="form-control" id="userid" placeholder="Enter userid" name="userid">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
    <div class="form-group form-check">
      <label class="form-check-label">
        <input class="form-check-input" type="checkbox" name="remember"> Remember me
      </label>
    </div>
    <button type="button" class="btn btn-primary" id="loginBtn">Submit</button>

</div>
<script>
$("#loginBtn").click(function(){
	if($("#userid").val()==""){
		alert("아이디를 입력하세요");
		$("#userid").focus();
		return false;
	}
	if($("#pwd").val()==""){
		alert("비밀번호를 입력하세요");
		$("#pwd").focus();
		return false;
	}
	$.ajax({
		type:"post",
		url: "login",
		data : {"userid" : $("#userid").val(), "pwd" :$("#pwd").val()},
		success:function(resp){//0 일반회원 1 관리자 -1 비회원 2: 비번오류
			if(resp.trim()==0){
				alert("일반회원")
				$(location).attr("href","../index.jsp");
			}else if(resp.trim()==1){
				alert("관리자")
				location.href="../admin/dashboard"
			}else if(resp.trim()==-1){
				alert("회원이 아닙니다. 회원가입하세요");
			}else if(resp.trim()==2){
				alert("비밀번호를 확인하세요")
			}
			
		},
		error : function(e){
			alert("error : " + e);
		}
		
	});
})


</script>


 <%@ include file="../include/footer.jsp" %>