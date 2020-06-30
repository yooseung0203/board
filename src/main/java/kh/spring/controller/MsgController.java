package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dto.MsgDTO;
import kh.spring.service.MsgService;

@Controller
@RequestMapping("/msg/")
public class MsgController {
	//@Autowired
	//private MsgDAO msgdao;
	
	@Autowired
	private MsgService msgservice;
	
	@Autowired
	private HttpSession session;
	
	//받은 쪽지함
	
	@RequestMapping("msg_list_sender")
	public String msglist_sender(HttpServletRequest request)throws Exception{
		String msg_receiver = (String) session.getAttribute("loginId");
		System.out.println(msg_receiver);
		List<MsgDTO> dto = msgservice.selectBySender(msg_receiver);
		request.setAttribute("list", dto);
		return "msg/msglist";
	}
	//관리자
	
	@RequestMapping("msg_list_admin")
	public String msglist_admin(HttpServletRequest request)throws Exception{
		String msg_receiver = (String) session.getAttribute("loginId");
		System.out.println(msg_receiver);
		List<MsgDTO> dto = msgservice.selectByAdmin(msg_receiver);
		request.setAttribute("list", dto);
		return "msg/msglist";
	}
	//보낸쪽지함
	
	@RequestMapping("msg_list_receiver")
	public String msglist_receiver(HttpServletRequest request)throws Exception{
		String msg_receiver = (String) session.getAttribute("loginId");
		System.out.println(msg_receiver);
		List<MsgDTO> dto = msgservice.selectByReceiver(msg_receiver);
		request.setAttribute("list", dto);
		return "msg/msglist";
	}
	
	@RequestMapping("msgWrite")
	public String msgWrite() {
		return "msg/msgWrite";
	}
	
	@RequestMapping("msgSend")
	public String msgSend(MsgDTO msgdto)throws Exception{
		String msg_sender=(String)session.getAttribute("loginId");
		msgdto.setMsg_sender(msg_sender);
		int result = msgservice.insert(msgdto);
		return "member/mypage";
	}
}


