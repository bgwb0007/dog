<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<main role="main">
	<section class="jumbotron text-center">
		<div class="container">
			<h1>분양신청 채팅목록</h1>
		</div>
	</section>

	<div class="container">
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">상대</th>
						<th scope="col">강아지 이름(견종)</th>
						<th scope="col">신청일</th>
						<th scope="col">채팅방입장</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">1</th>
						<td>Mark</td>
						<td>Otto</td>
						<td>20-11-12</td>
						<td><a class="btn btn-primary">입장하기</a></td>
					</tr>
					<c:forEach items="${chats }" var="chat">
						<tr>
							<th scope="row">1</th>
							<td>
								<c:choose>
									<c:when test="${chat.buyerid == sessionScope.user.userid}">
										${chat.sellerid }
								</c:when>
								<c:otherwise>
									${chat.buyerid }
								</c:otherwise>
								</c:choose></td>
							<td>${chat.productname }(${chat.productcategory })</td>
							<td>${chat.createddate }</td>
							<td><a href="/dog/chat/on?chatid=${chat.id }" class="btn btn-primary">입장하기${chat.id }</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</main>



<%@ include file="../include/footer.jsp"%>
