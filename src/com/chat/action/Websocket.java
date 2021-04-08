package com.chat.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.chat.model.ChatDAO;
import com.chat.model.ChatDAOImpl;
import com.chat.model.Message;

// WebSocket 호스트 설정
@ServerEndpoint("/broadsocket")
public class Websocket {
// 접속 된 클라이언트 WebSocket session 관리 리스트
	private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	private static Map<Integer, List<Session>> room_map = Collections
			.synchronizedMap(new HashMap<Integer, List<Session>>());

// 메시지에서 유저 명을 취득하기 위한 정규식 표현
	private static Pattern pattern = Pattern.compile("^\\{\\{.*?\\}\\}");

// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void handleOpen(Session userSession) {
// 클라이언트가 접속하면 WebSocket세션을 리스트에 저장한다.
		sessionUsers.add(userSession);
// 콘솔에 접속 로그를 출력한다.
		System.out.println("client is now connected...");
	}

// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		if (message.substring(0, 1).equals("r")) {
			String[] arr = message.split(",");
			// arr[0] = r , arr[1] = chatid, arr[2] = userid
			System.out.println("chat 정보");
			System.out.println(arr[1]);
			System.out.println(arr[2]);
			int chatid = Integer.parseInt(arr[1]);

			List<Session> temp_sessionUsers = Collections.synchronizedList(new ArrayList<>());
			// 이미 방 세션이 있으면
			if (room_map.containsKey(chatid)) {
				temp_sessionUsers = room_map.get(chatid);
				temp_sessionUsers.add(userSession);
				room_map.put(chatid, temp_sessionUsers);
			} else {
				temp_sessionUsers.add(userSession);
				room_map.put(chatid, temp_sessionUsers);
			}
		} else {
			// arr[0] = chatid, arr[1] = userid, arr[2] = massage내용
			String[] arr = message.split(",", 3);
			// 메시지 내용을 콘솔에 출력한다.
			System.out.println(message);
			// chatid 저장
			int chatid = Integer.parseInt(arr[0]);
			// 초기 유저 명
			String name = "anonymous";
			// 메시지로 유저 명을 추출한다.
			Matcher matcher = pattern.matcher(arr[2]);
			// 메시지 예: {{유저명}}메시지
			if (matcher.find()) {
				name = matcher.group();
			}
			// 클로져를 위해 변수의 상수화
			final String msg = arr[2].replaceAll(pattern.pattern(), "");
			final String username = name.replaceFirst("^\\{\\{", "").replaceFirst("\\}\\}$", "");
			System.out.println(msg);

			// db에 message 저장
			Message insertMessage = new Message();
			insertMessage.setUserid(arr[1]);
			insertMessage.setChatid(chatid);
			insertMessage.setText(msg);
			// 생성일 저장
			SimpleDateFormat format1 = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
			Date now = new Date();
			String format_time1 = format1.format(now.getTime());
			insertMessage.setCreateddate(format_time1);

			ChatDAO dao = ChatDAOImpl.getInstance();
			dao.messageInsert(insertMessage);

			// session관리 리스트에서 Session을 취득한다.
			room_map.get(chatid).forEach(session -> {
				// 리스트에 있는 세션과 메시지를 보낸 세션이 같으면 메시지 송신할 필요없다.
				if (session == userSession) {
					return;
				}
				try {
					// 리스트에 있는 모든 세션(메시지 보낸 유저 제외)에 메시지를 보낸다. (형식: 유저명 => 메시지)
					session.getBasicRemote().sendText(username + " => " + msg);
				} catch (IOException e) {
					// 에러가 발생하면 콘솔에 표시한다.
					e.printStackTrace();
				}
			});
		}

	}

// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void handleClose(Session userSession) {
// session 리스트로 접속 끊은 세션을 제거한다.
		sessionUsers.remove(userSession);
// 콘솔에 접속 끊김 로그를 출력한다.
		System.out.println("client is now disconnected...");
	}
}