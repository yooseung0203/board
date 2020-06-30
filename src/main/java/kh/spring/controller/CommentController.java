package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dto.CommentDTO;
import kh.spring.service.CommentService;

@Controller
@RequestMapping("/comment/")
public class CommentController {


	@Autowired
	private CommentService cservice;
	
	@Autowired
	private HttpSession session;
	
	@ExceptionHandler
	public String exceptionHandler(Exception e) { //일괄적 예외처리 : ExceptionHandler를 통해서 클래스 내의 모든 메서드의 예외를 받을 수 있다.(다른 메서드에는 모두 throws Exception을 붙여줌)
		e.printStackTrace();
		System.out.println("Exception Handler : 에러가 발생했습니다.");
		return "error";
	}
	
	@RequestMapping("replyProc")
	public String replyProc(CommentDTO cdto) throws Exception {
//		MemberDTO mdto = (MemberDTO) session.getAttribute("loginInfo");
//		cdto.setWriter(mdto.getId());
		System.out.println("글번호"+cdto.getParent_seq());
		System.out.println("작성자"+cdto.getWriter());
		cservice.replyInsert(cdto);
		
		return "redirect:/board/contents?seq="+cdto.getParent_seq();
	}
	
	@RequestMapping("replyList")
	public String replyList(int parent_seq, Model model) throws Exception {
		List<CommentDTO> dto = cservice.replyList(parent_seq);
		model.addAttribute("replyList",dto);
		
		return  "redirect:/board/contents?seq="+parent_seq;
		
		
	}
	
	
	

}
