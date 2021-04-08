<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="container">
	<h1>판매자 : ${chat.sellerid}, 구매자 : ${chat.buyerid}, 강아지이름 :
		${chat.productname}(${chat.productcategory})</h1>
</div>


<div class="container">
	<div class="" id="chat-container"
		style='min-height: 600px; max-height: 600px; overflow: scroll; overflow-x: hidden; background: #9bbbd4;padding-top: 2rem;'>
		<c:forEach items="${messages }" var="message">
			<div class="col-12">
				<c:choose>
					<c:when test="${message.userid eq sessionScope.user.userid }">
						<div class='my-chat'
							style='margin-left: auto; text-align: right; max-width: 70%;'>
							<div class='chat'
								style='display: inline-block; font-size: 1.3rem; background: #F7E600; border-radius: 10px 10px 10px 10px; padding: 5px;'>${message.text }</div>
					</c:when>
					<c:otherwise>
						<div class='receive-chat'
							style='margin-right: auto; text-align: left; max-width: 70%;'>
							<div class='chat'
								style='display: inline-block; font-size: 1.3rem; background: #ffffff; border-radius: 10px 10px 10px 10px; padding: 5px;'>${message.userid }
								: ${message.text }</div>
					</c:otherwise>
				</c:choose>


				<div class='chat-info' style='font-size: 0.8rem;'>${message.createddate }</div>
			</div>
	</div>
	</c:forEach>
</div>
</div>

<div class="container">
	<div class="row"></div>

	<br>
	<form>
		<div class="form-row">
			<div class="col-8">
				<input class="form-control" id="textMessage" type="text">
			</div>
			<div class="col-4">
				<input class="form-control btn-secondary" onclick="sendMessage()"
					value="Send" type="button">
			</div>
			<input id="user" type="hidden" value="${me}">
		</div>
	</form>

	<script type="text/javascript">
		// 「WebSocketEx」는 프로젝트 명
		// 「broadsocket」는 호스트 명
		// WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
		var webSocket = new WebSocket(
				"ws://localhost:8888/dog/broadsocket");
		
		// WebSocket 서버와 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
			const chatid = ${chat.id }
			const userid = ${me}
			//접속정보 메시지를 보낸다 ('r', chatid, userid)
			webSocket.send("r,"+ chatid + "," + userid);
			
		};
		// WebSocket 서버와 접속이 끊기면 호출되는 함수
		webSocket.onclose = function(message) {
			// 콘솔 텍스트에 메시지를 출력한다.
			
			//messageTextArea.value += "Server Disconnect...\n";

		};
		// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
		webSocket.onerror = function(message) {
			// 콘솔 텍스트에 메시지를 출력한다.
		};
		/// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
		webSocket.onmessage = function(message) {
		
		const temp = message.data;
		const msg = temp.split(',',3);
		const userid = msg[0];
		const datetime = msg[1];
		const text = msg[2];
		
		// 콘솔 텍스트에 메시지를 출력한다.
		var $chat =$("<div class='col-12'><div class='receive-chat'style='margin-right: auto; text-align: left;max-width: 70%;'><div class='chat' style='display: inline-block; font-size: 1.3rem;background: #ffffff;border-radius: 10px 10px 10px 10px;padding: 5px;'>" + userid + " : " + text + "</div><div class='chat-info' style='font-size: 0.8rem;'>"+ datetime +"</div></div></div>");
		$('#chat-container').append($chat);
		$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
		};
		
		function getFormatDate(date){
			var year = date.getFullYear()+"";
			var yy = year.substr(2,2);
			var month = (1+date.getMonth());
			month = month >= 10? month : '0'+month;
			var day = date.getDate();
			day = day >= 10 ? day : '0'+day;
			var hours = date.getHours();
			hours = hours >= 10? hours : '0'+hours;
			var minutes = date.getMinutes();
			minutes = minutes >= 10? minutes : '0'+minutes;
			var seconds = date.getSeconds();
			seconds = seconds >= 10? seconds : '0'+seconds;
			return yy+'-'+month+'-'+day+' '+hours+':'+minutes+':'+seconds;
		}
		// Send 버튼을 누르면 호출되는 함수
		function sendMessage() {
			// 유저명 텍스트 박스 오브젝트를 취득
			var user = document.getElementById("user");
			// 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득
			var message = document.getElementById("textMessage");
			// 콘솔 텍스트에 메시지를 출력한다
			
			var today = getFormatDate(new Date());
			
			var $chat = $("<div class='col-12'><div class='my-chat' style='margin-left: auto; text-align: right;max-width: 70%;'><div class='chat' style='display: inline-block; font-size: 1.3rem; background: #F7E600;border-radius: 10px 10px 10px 10px;padding: 5px;'>"+ message.value + "</div><div class='chat-info' style='font-size: 0.8rem;'>" + today + "</div></div></div>");
			$('#chat-container').append($chat);
			$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
			
			// WebSocket 서버에 메시지를 전송(형식 「{{유저명}}메시지」)
			webSocket.send(${chat.id} +","+${me}+ ",{{" + user.value + "}}" + message.value);
			// 송신 메시지를 작성한 텍스트 박스를 초기화한다.
			message.value = "";
		}
		// Disconnect 버튼을 누르면 호출되는 함수
		function disconnect() {
			// WebSocket 접속 해제
			webSocket.close();
		}
	</script>
</div>

<%@ include file="../include/footer.jsp"%>