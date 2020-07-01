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
		return "msg/msgListSender";
	}
	//관리자
	
	@RequestMapping("msg_list_admin")
	public String msglist_admin(HttpServletRequest request)throws Exception{
		String msg_receiver = (String) session.getAttribute("loginId");
		System.out.println(msg_receiver);
		List<MsgDTO> dto = msgservice.selectByAdmin(msg_receiver);
		request.setAttribute("list", dto);
		return "msg/msgListSender";
	}
	//보낸쪽지함
	
	@RequestMapping("msg_list_receiver")
	public String msglist_receiver(HttpServletRequest request)throws Exception{
		String msg_receiver = (String) session.getAttribute("loginId");
		System.out.println(msg_receiver);
		List<MsgDTO> dto = msgservice.selectByReceiver(msg_receiver);
		request.setAttribute("list", dto);
		return "msg/msgListReceiver";
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
	
	@RequestMapping("msgView")
	public String msgView(HttpServletRequest request,int msg_seq)throws Exception{
		
		MsgDTO msgDTO = msgservice.selectBySeq(msg_seq);
		int result = msgservice.updateView(msg_seq);
		request.setAttribute("msgView", msgDTO);
		return "msg/msgView";
	}
	
	//받은쪽지함 삭제
	
	@RequestMapping("msgReceiverDel")
	public String ReceiverDel(int msg_seq)throws Exception{
		int result = msgservice.receiver_del(msg_seq);
		
		return "redirect:msg_list_sender";
	}
	
	//보낸쪽지함 삭제
	@RequestMapping("msgSenderDel")
	public String SenderDel(int msg_seq)throws Exception{
		System.out.println(msg_seq);
		int result = msgservice.sender_del(msg_seq);

		return "redirect:msg_list_receiver";
	}
}


