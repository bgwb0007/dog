<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="jumbotron jumbotron-fluid mb-5">
	<div class="container">
		<h1>분양등록</h1>
	</div>
</div>
<div class="container">

	<form action="pinsert" class="form-horizontal" method="post"
		enctype="multipart/form-data">
		<!-- 	 		<div class="form-group row">
				<label class="col-sm-2">productId</label>
				<div class="col-sm-3">
					<input type="text" id="productId"  name="productId" 
					 class="form-control" >
				</div>
			</div>  -->
		<div class="form-group row">
			<label class="col-sm-2">강아지 이름</label>
			<div class="col-sm-3">
				<input type="text" id="name" name="name" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">나이(개월)</label>
			<div class="col-sm-3">
				<input type="text" id="age" name="age" class="form-control">
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2">견종</label>
			<div class="col-sm-3">
				<input type="text" id="category" name="category"
					class="form-control">
			</div>
		</div>



		<fieldset class="form-group">
			<div class="row">
				<legend class="col-form-label col-sm-2 pt-0">성별</legend>
				<div class="col-sm-10">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="gender"
							id="gridRadios1" value="Mail" checked> <label
							class="form-check-label" for="gridRadios1"> Mail </label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="gender"
							id="gridRadios2" value="Femail"> <label
							class="form-check-label" for="gridRadios2"> Femail </label>
					</div>
				</div>
			</div>
		</fieldset>
		
		<div class="form-group row">
			<label class="col-sm-2">상세설명</label>
			<div class="col-sm-5">
				<textarea id="description" name="description" cols="30" rows="15"
					class="form-control">
* 강아지와 관련된 특이사항 / 입양관련 정보*<br>
출생 특이사항 : 
예방접종 유무 : </textarea>
			</div>
		</div>
		

		<div class="form-group row">
			<label class="col-sm-2">사진</label>
			<div class="col-sm-5">
				<input type="file" name="productImage" class="form-control"
					id="productImage">
			</div>
		</div>

		



		<div class="form-group row">
			<div class="col-sm-offset-2 col-sm-10 ">
				<input type="submit" class="btn btn-primary" value="분양등록">
			</div>
		</div>
		<input type="hidden" id="status" name="status" class="form-control" value="on">
	</form>
</div>
<%@ include file="../include/footer.jsp"%>
