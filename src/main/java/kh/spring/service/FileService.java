package kh.spring.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.FileDAO;
import kh.spring.dto.FileDTO;



@Service
public class FileService {
	
	@Autowired
	private FileDAO fdao;
	
	@Autowired
	private HttpSession session;
	
	public List<FileDTO> getFileList(int seq){
		return fdao.getFileList(seq);
	}


}
