package kh.spring.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kh.spring.dto.MemberDTO;
import kh.spring.dto.TempDTO;

@Controller
@RequestMapping("/file/")
public class Study_FileController {

	// MultipartHttpServletRequest req - 에러 원인이다? 우리가 쓰던 방식과 가장 닮아있는 방식인데
	//String writer = req.getParameter("writer");
	//String contents = req.getParameter("contents");
	//MultipartFile file = req.getFile("file"); // getFile메서드는 MultipartFile형으로  return되므로 맞는 자료형의 변수로 받아줘야함

	//System.out.println(writer + ":" + contents);
	//System.out.println(file.getOriginalFilename());

	@Autowired
	private HttpSession session;

	@RequestMapping("filehome")
	public String toFilehome(){
		return "file/filehome";
	}

	@RequestMapping("fileUpload")
	public String fileUpload(TempDTO dto) throws Exception{
		//MultipartFile file = req.getFile("file"); // getFile메서드는 MultipartFile형으로  return되므로 맞는 자료형의 변수로 받아줘야함

		//upload폴더안에 또 다른 폴더 만들러면 어떤식으로 생성해야되나요 예를들면 프로필변경할때 회원별로 폴더 만들라고하면 어떤식으로 하는지 궁금합니다의 답변
		MemberDTO mdto = (MemberDTO) session.getAttribute("loginInfo");
		String id = mdto.getId();
		String filePath;
		//		if(id.isEmpty()) {
		//			filePath = session.getServletContext().getRealPath("upload/anonymous/");
		//		}else {
		filePath = session.getServletContext().getRealPath("upload/" + id + "/");
		//		}

		//png만 받고 싶을때는?
		//		String ext = dto.getFile().getOriginalFilename().substring(dto.getFile().getOriginalFilename().lastIndexOf(".")+1);
		//		if(ext.contentEquals("jpg")) { //확장자가 jpg 이면
		//			return "warning";
		//		}

		//String filePath = session.getServletContext().getRealPath("");
		File tempFilePath = new File(filePath);
		if(!tempFilePath.exists()) {tempFilePath.mkdir();} 
		String systemFileName= System.currentTimeMillis() + "_" + dto.getFile().getOriginalFilename();
		File targetLoc = new File(filePath + "/" + systemFileName);
		dto.getFile().transferTo(targetLoc);
		System.out.println("filePath"+filePath);
		System.out.println("systemFileName" + systemFileName);
		return "redirect:filehome";
	}

	@RequestMapping("filesUpload")
	public String filesUpload(MultipartFile[] files) throws Exception {
		System.out.println(files.length);
		String filePath = session.getServletContext().getRealPath("upload");
		for(MultipartFile file:files) {
			if(!file.isEmpty()) { //파일이 비어있지 않을 때만 저장		
				UUID uuid = UUID.randomUUID(); //32글자의 랜덤스트링을 생성 
				String systemFileName= uuid.toString() + "_" + file.getOriginalFilename();
				File targetLoc = new File(filePath + "/" + systemFileName);
				file.transferTo(targetLoc);
			}
		}
		return "redirect:filehome";
	}
}
