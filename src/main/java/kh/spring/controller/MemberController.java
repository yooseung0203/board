package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;
import kh.spring.service.MemberService;
import kh.spring.service.MsgService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	@RequestMapping("test")
	public String test() {
		return "member/test";
	}

	@Autowired
	private MemberDAO mdao;

	@Autowired
	private MsgService msgservice;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService mservice;

	//HttpServletRequest는 Autowired하지 않는다. 
	//리퀘스트는 일회용으로 사용하는 데이터이기 때문에 여러 유저가 접속했을 때 문제가 될 수 있다.

	@RequestMapping("signup")
	public String toSignUp() {
		return "member/signup";
	}

	@RequestMapping("signUpProc")
	public String signUpProc(MemberDTO dto) throws Exception {
		int result= mservice.insert(dto);
		
		String id = dto.getId();
		msgservice.insertWelcome(id);
		return "redirect:/";
	}

	// AJAX
	// Insert / Select
	//ResponseBody를 쓰면 반환되는 String을주소로 인식하지 않고, 반환되야하는 '값'으로 인식해준다. AJAX를 이용할때 거의 필수적.

	// AJAX 응답을 해야할 경우
	// @RequestMapping(value="modifyinfo",produces="application/json;charset=utf8") 
	// 한글깨짐현상을 방지하기 위해 produces옵션을 통해 응답(Response)에 대한 인코딩 설정을 해줌. 지금은 json타입이고 utf8로 보내겠다는 의미
	// 텍스트로 보내는 경우 produces="text/plain;charset=utf8"로 작성.
	@ResponseBody
	@RequestMapping("idDupleCheck")
	public String toidDupleCheck(String id) throws Exception {
		System.out.println(id);
		boolean result=mservice.duplCheck(id);
		return String.valueOf(result);
	}

	@RequestMapping("loginProc")
	public String toLogin(String id, String pw) throws Exception { 
		//public String toLogin(HttpSession session, String id, String pw) { //필요할 때마다 session을 추가해야하는 번거로움. 그래서 Autowired함 
		// PW = PW 암호화
		// DAO로부터 검증로직
		System.out.println("로그인 시도 id: "+id);
		boolean result = mservice.loginCheck(id,pw);
		if (result) {
			MemberDTO info = mservice.selectById(id);
			session.setAttribute("loginInfo", info); //dto를 담으면 여러 정보를 활용하기 좋다.
			session.setAttribute("cpage", 1);
			this.session.setAttribute("loginId", id);
			return "redirect:mypage";
		}else {
			return "redirect:/";
		}
	}
	

	@RequestMapping("mypage")
	public String toMypage()  {
		return "member/mypage";
	}

	@RequestMapping("modifyinfo") 
	public String toModifyInfo(Model model) throws Exception {
		model.addAttribute("list", session.getAttribute("loginInfo"));
		return "member/modifyinfo";
	}
	
	@RequestMapping("modifyInfoProc")
	public String modifyInfoProc(MemberDTO dto) throws Exception{
		mservice.update(dto);
		session.removeAttribute("loginInfo");
		MemberDTO info = mservice.selectById(dto.getId());
		session.setAttribute("loginInfo", info);
		
		return "member/mypage";
	}
	

	@RequestMapping("logout")
	public String LogOut() {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("memberout")
	public String toMemberOut() throws Exception {
		MemberDTO dto = (MemberDTO) session.getAttribute("loginInfo");
		String id = dto.getId();
		
		mservice.delete(id);
		return "redirect:/";
	}
	
	
	
	
}
