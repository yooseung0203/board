package kh.spring.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import kh.spring.config.HttpSessionConfigurator;

@ServerEndpoint(value="/chat/chat",configurator=HttpSessionConfigurator.class) 
public class WebChat { 
	// SErverEndpoint : 이 클래스를 클라이언트와 통신하는 웹소캣으로 쓰겠다.
	// ws를 쓰는데 websocket에 줄임말이다?

	// 웹소켓리퀘스트(HTTP리퀘스트가 아님!!)가 들어오는 순간 OnOpen을 실행

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

	// HashSet 인스턴스를 만들때 일반 HashSet을 동기화가 가능한 업그레이드된 HashSet으로 바꿔줌 Collections.synchronizedSet
	//	매개변수 client는 onConnect메서드가 없어지면 사라진다.
	//	어디서 온 메세지인지 알아야하기 때문에 session을 따로 저장을 해놓아야 한다.
	//	List 컬렉션을 사용하여야 한다 -> 근데 중복이 있으면 안된다 -> HashSet을 이용

	//사람 수만큼.. 들어온 사람이 10명이면 웹챗 클래스도 10개고 클라이언츠도 10개가 되버림. 그래서 clients를 stastic을 줘야한다

	private HttpSession session;

	@OnOpen
	public void onConnect(Session client, EndpointConfig config) {
		//httpsession이 아니고 websocketsession임 -> import한 부분 참고

		System.out.println();
		System.out.println(client.getId() + "클라이언트가 접속했습니다.");
		// F5 누르면 이전 세션은 종료되고 새로운 세션을 발급받음. 그래서 새 아이디를 발급받게 됨
		clients.add(client); 
		this.session = (HttpSession) config.getUserProperties().get("session");
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		//System.out.println(session.getId() + " : " + message);
		//누가 보냈는지(세션값), 뭘 보냈는지(메세지값)

		String id = (String) this.session.getAttribute("loginId");
		System.out.println("onMessage : " + id);

		synchronized (clients) {
			for(Session client:clients) { // 이렇게 하면 모든 사용자들에게 뿌려짐
				if(!client.getId().contentEquals(session.getId())) { // 메세지를 작성한 사람은 받지 않도록
					Basic basic = client.getBasicRemote();
					//sesiion으로부터 basic이라는 걸 꺼내 client에게 보낸다.
					try {
						basic.sendText(id + " : " + message);
						//텍스트를 보낸다. client는 메세지는 보낸 session의 Basic이므로 메세지를 보낸 사람이 메세지를 받는 것(ECHO).

					}catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				//synchronized 안에 있기 때문에 for문이 도는 동안 remove가 불가능해짐???
			}
		}

	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		clients.remove(session);
	}

}