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

// WebSocket ȣ��Ʈ ����
@ServerEndpoint("/broadsocket")
public class Websocket {
// ���� �� Ŭ���̾�Ʈ WebSocket session ���� ����Ʈ
	private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	private static Map<Integer, List<Session>> room_map = Collections
			.synchronizedMap(new HashMap<Integer, List<Session>>());

// �޽������� ���� ���� ����ϱ� ���� ���Խ� ǥ��
	private static Pattern pattern = Pattern.compile("^\\{\\{.*?\\}\\}");

// WebSocket���� �������� �����ϸ� ��û�Ǵ� �Լ�
	@OnOpen
	public void handleOpen(Session userSession) {
// Ŭ���̾�Ʈ�� �����ϸ� WebSocket������ ����Ʈ�� �����Ѵ�.
		sessionUsers.add(userSession);
// �ֿܼ� ���� �α׸� ����Ѵ�.
		System.out.println("client is now connected...");
	}

// WebSocket���� �޽����� ���� ��û�Ǵ� �Լ�
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		if (message.substring(0, 1).equals("r")) {
			String[] arr = message.split(",");
			// arr[0] = r , arr[1] = chatid, arr[2] = userid
			System.out.println("chat ����");
			System.out.println(arr[1]);
			System.out.println(arr[2]);
			int chatid = Integer.parseInt(arr[1]);

			List<Session> temp_sessionUsers = Collections.synchronizedList(new ArrayList<>());
			// �̹� �� ������ ������
			if (room_map.containsKey(chatid)) {
				temp_sessionUsers = room_map.get(chatid);
				temp_sessionUsers.add(userSession);
				room_map.put(chatid, temp_sessionUsers);
			} else {
				temp_sessionUsers.add(userSession);
				room_map.put(chatid, temp_sessionUsers);
			}
		} else {
			// arr[0] = chatid, arr[1] = userid, arr[2] = massage����
			String[] arr = message.split(",", 3);
			// �޽��� ������ �ֿܼ� ����Ѵ�.
			System.out.println(message);
			// chatid ����
			int chatid = Integer.parseInt(arr[0]);
			// �ʱ� ���� ��
			String name = "anonymous";
			// �޽����� ���� ���� �����Ѵ�.
			Matcher matcher = pattern.matcher(arr[2]);
			// �޽��� ��: {{������}}�޽���
			if (matcher.find()) {
				name = matcher.group();
			}
			// Ŭ������ ���� ������ ���ȭ
			final String msg = arr[2].replaceAll(pattern.pattern(), "");
			final String username = name.replaceFirst("^\\{\\{", "").replaceFirst("\\}\\}$", "");
			System.out.println(msg);

			// db�� message ����
			Message insertMessage = new Message();
			insertMessage.setUserid(arr[1]);
			insertMessage.setChatid(chatid);
			insertMessage.setText(msg);
			// ������ ����
			SimpleDateFormat format1 = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
			Date now = new Date();
			String format_time1 = format1.format(now.getTime());
			insertMessage.setCreateddate(format_time1);

			ChatDAO dao = ChatDAOImpl.getInstance();
			dao.messageInsert(insertMessage);

			// session���� ����Ʈ���� Session�� ����Ѵ�.
			room_map.get(chatid).forEach(session -> {
				// ����Ʈ�� �ִ� ���ǰ� �޽����� ���� ������ ������ �޽��� �۽��� �ʿ����.
				if (session == userSession) {
					return;
				}
				try {
					// ����Ʈ�� �ִ� ��� ����(�޽��� ���� ���� ����)�� �޽����� ������. (����: ������ => �޽���)
					session.getBasicRemote().sendText(username + " => " + msg);
				} catch (IOException e) {
					// ������ �߻��ϸ� �ֿܼ� ǥ���Ѵ�.
					e.printStackTrace();
				}
			});
		}

	}

// WebSocket�� �������� ������ ����� ��û�Ǵ� �Լ�
	@OnClose
	public void handleClose(Session userSession) {
// session ����Ʈ�� ���� ���� ������ �����Ѵ�.
		sessionUsers.remove(userSession);
// �ֿܼ� ���� ���� �α׸� ����Ѵ�.
		System.out.println("client is now disconnected...");
	}
}