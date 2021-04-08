<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="container">
	<h1>판매자 : ${chat.sellerid}, 구매자 : ${chat.buyerid}, 강아지이름 :
		${chat.productname}(${chat.productcategory})</h1>
</div>
<div class="container">
	<div class="row">
		<!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
		<textarea id="messageTextArea" rows="15" cols="80" class="form-control"><c:forEach items="${messages }" var="message">(${message.createddate })${message.userid } => ${message.text }
</c:forEach></textarea>
	</div>

<br>
	<form>
		<div class="form-row">
			<div class="col-8">
				<input class="form-control" id="textMessage" type="text">
			</div>
			<div class="col-4">
				<input class="form-control btn-secondary" onclick="sendMessage()" value="Send" type="button">
			</div>


			<input id="user" type="hidden" value="${user.userid}">
		</div>
	</form>

	<script type="text/javascript">
		// 「WebSocketEx」는 프로젝트 명
		// 「broadsocket」는 호스트 명
		// WebSocket 오브젝트 생성 (자동으로 접속 시작한다. - onopen 함수 호출)
		var webSocket = new WebSocket(
				"ws://localhost:8888/dog/broadsocket");
		// 콘솔 텍스트 에리어 오브젝트
		var messageTextArea = document.getElementById("messageTextArea");
		// WebSocket 서버와 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			// 콘솔 텍스트에 메시지를 출력한다.
			//messageTextArea.value += "Server connect...\n";
			messageTextArea.scrollTop = messageTextArea.scrollHeight;
			//접속정보 메시지를 보낸다 (r, chatid, userid)
			webSocket.send("r,"+${chat.id}+ "," + ${user.userid});
			
		};
		// WebSocket 서버와 접속이 끊기면 호출되는 함수
		webSocket.onclose = function(message) {
			// 콘솔 텍스트에 메시지를 출력한다.
			
			//messageTextArea.value += "Server Disconnect...\n";

		};
		// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
		webSocket.onerror = function(message) {
			// 콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += "error...\n";
		};
		/// WebSocket 서버로 부터 메시지가 오면 호출되는 함수
		webSocket.onmessage = function(message) {
			// 콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += message.data + "\n";
			messageTextArea.scrollTop = messageTextArea.scrollHeight;
		};
		// Send 버튼을 누르면 호출되는 함수
		function sendMessage() {
			// 유저명 텍스트 박스 오브젝트를 취득
			var user = document.getElementById("user");
			// 송신 메시지를 작성하는 텍스트 박스 오브젝트를 취득
			var message = document.getElementById("textMessage");
			// 콘솔 텍스트에 메시지를 출력한다.
			messageTextArea.value += user.value + "(나) => " + message.value
					+ "\n";
			messageTextArea.scrollTop = messageTextArea.scrollHeight;
			// WebSocket 서버에 메시지를 전송(형식 「{{유저명}}메시지」)
			webSocket.send(${chat.id} +","+${me.userid }+ ",{{" + user.value + "}}" + message.value);
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
