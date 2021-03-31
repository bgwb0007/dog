<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
 <div class="jumbotron jumbotron-fluid mb-5">
   	<div class="container">
   		<h1>게시글 상세보기</h1>
   	</div>
  </div>
<div class="container">

  <form action="boardupdate" method="post" id="frm">
   <div class="form-group">
      <label for="num">num:</label>
      <input type="text" class="form-control" id="num"  name="num" 
      value="${board.num}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="writer">Userid:</label>
      <input type="text" class="form-control" id="userid"  
      name="userid" value="${sessionScope.user.userid }" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="subject">Subject:</label>
      <input type="text" class="form-control" id="subject" 
      placeholder="Enter subject" name="subject" value="${board.subject }">
    </div>
    <div class="form-group">
      <label for="eamil">Email:</label>
      <input type="text" class="form-control" id="email" 
      placeholder="Enter eamil" name="email" value="${board.email }">
    </div>
    <div class="form-group">
      <label for="content">Content:</label>
      <textarea class="form-control" rows="5" id="content" 
      name="content">${board.content }</textarea>
    </div>
  <button  type="submit"  class="btn btn-primary">수정하기</button>
  <button  type="reset"  class="btn btn-secondary">취소하기</button>
  </form>
  </div>
<%@ include file="../include/footer.jsp"%>