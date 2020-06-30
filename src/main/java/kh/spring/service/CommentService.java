package kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.CommentDAO;
import kh.spring.dto.CommentDTO;

@Service
public class CommentService {
	@Autowired
	CommentDAO cdao;
	
	public int replyInsert(CommentDTO dto) {
		return cdao.insert(dto);
	}
	
	public List<CommentDTO> replyList(int parent_seq) throws Exception {
		List<CommentDTO> dto = cdao.selectByParentSeq(parent_seq);
		return dto;
	}

}
