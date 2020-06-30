package kh.spring.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;


public class HttpSessionConfigurator extends Configurator  {

	@Override
	public void modifyHandshake(
			ServerEndpointConfig sec, // ServerEndPoint까지 전달되는 변수
			HandshakeRequest request,
			HandshakeResponse response
			){
		HttpSession session = (HttpSession)request.getHttpSession();  //object형이므로 캐스팅 필요
		sec.getUserProperties().put("session", session);
		
		//Map으로 구성되어 있으므로 put으로 값을 집어넣는다.


	}
}
