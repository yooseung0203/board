package kh.spring.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.CommentDTO;
import kh.spring.dto.FileDTO;
import kh.spring.dto.FilesDTO;
import kh.spring.dto.MemberDTO;
import kh.spring.service.BoardService;
import kh.spring.service.CommentService;
import kh.spring.service.FileService;


//컨트롤러에도 매핑을 걸어줄 수 있다.
@Controller
@RequestMapping("/board/")
public class BoardController{
	@Autowired
	private BoardDAO bdao;

	@Autowired
	private BoardService bservice;

	@Autowired
	private FileService fservice;
	
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



	@RequestMapping("list")
	public String list(HttpServletRequest request) throws Exception {

		try { 
			session.setAttribute("cpage", Integer.parseInt(request.getParameter("cpage")));
		} catch (Exception e) {}
		int cpage= (int) session.getAttribute("cpage");
		List<BoardDTO> dto = bservice.selectByPage(cpage);
		String navi = bservice.navi(cpage);
		request.setAttribute("list", dto);
		request.setAttribute("navi", navi);
		return "board/list";
	}


	@RequestMapping("write")
	public String toWrite(){
		return "board/write";
	}

	@RequestMapping("boardWrite")
	public String boardWriteProc(HttpServletRequest req, BoardDTO bdto, FilesDTO fsdto) throws Exception{

		MemberDTO mdto = (MemberDTO) session.getAttribute("loginInfo");
		bdto.setWriter(mdto.getId());
		bdto.setIp_addr(req.getRemoteAddr());		

		/*파일*/         
		String realPath = session.getServletContext().getRealPath("upload/");

		File filePath = new File(realPath);
		if(!filePath.exists()) {
			filePath.mkdir(); //폴더 만들기
		}


		/** 파일 업로드 **/      
		List <FileDTO> filelist = new ArrayList<FileDTO>();
		if(fsdto.getFiles().length != 0) { // 한개라도 폴더가 들어가면 나옴
			int num = 0;
			for(MultipartFile file : fsdto.getFiles()) {
				if(!file.isEmpty()) { // 파일이 있는지 없는지 확인
					FileDTO singleFDTO= new FileDTO();
					UUID uuid = UUID.randomUUID();

					singleFDTO.setOriName(file.getOriginalFilename());
					
					singleFDTO.setSysName(uuid+"_"+file.getOriginalFilename());               
					String systemFileName = uuid+"_"+file.getOriginalFilename();
					System.out.println(systemFileName);
					File flieDownload = new File(realPath + "/" + systemFileName);
					file.transferTo(flieDownload); // 파일 생성
					System.out.println(singleFDTO.getSysName());
					filelist.add(singleFDTO);
				}            
			}
		}

		bservice.writeProc(filelist,bdto,realPath);
		return "redirect:list";
	}

	@RequestMapping("contents")
	public String contents(int seq, HttpServletRequest request) throws Exception {
		BoardDTO dto = bservice.selectBySeq(seq);
		request.setAttribute("contents", dto);
		request.setAttribute("fileList", fservice.getFileList(seq));
		
		List<CommentDTO> cdto = cservice.replyList(seq);
		
		request.setAttribute("replyList",cdto);
		
		return "board/contents";
	}

	@RequestMapping("delete")
	public String delete(String seq) throws Exception{
		bservice.delete(seq);
		return "redirect:list";
	}

	@RequestMapping("modify")
	public String toModify(int seq, HttpServletRequest request) throws Exception {
		BoardDTO dto = bservice.selectBySeq(seq);
		request.setAttribute("contents", dto);
		return "board/modify";
	}

	@RequestMapping("modifyProc")
	public String Modify(BoardDTO dto) throws Exception {
		bservice.update(dto);
		return "redirect:contents?seq="+dto.getSeq();
	}


}
