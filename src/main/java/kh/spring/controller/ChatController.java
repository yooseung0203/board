package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dto.MemberDTO;


@Controller
@RequestMapping("/chat/")
public class ChatController {

	@Autowired
	private HttpSession session;

	@RequestMapping("chat")
	public String toChat() {
		System.out.println("채팅에 진입하려는 ID : "+session.getAttribute("loginId"));
		return "/chat/chat";
	}
	
}
