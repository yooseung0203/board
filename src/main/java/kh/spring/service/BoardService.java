package kh.spring.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.FileDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FileDTO;
import kh.spring.statics.Configuration;

@Service
public class BoardService { // 게시판과 관련된 Business Logic
	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private FileDAO fdao;
	
	
	@Transactional("txManager")
	public void writeProc(List<FileDTO> fileList, BoardDTO bdto, String realPath) throws Exception {
		
		int seq = bdao.getNextVal();
		bdto.setSeq(seq);
		bdao.insert(bdto);
		for(FileDTO file : fileList) {
			file.setParent_seq(seq);
			fdao.insert(file);
		}
	}
	
	
	
	public List<BoardDTO> selectByPage(int cpage) throws Exception{
		List<BoardDTO> dto = bdao.selectByPage(cpage);
		return dto;
	}
	
	public String getPageNav(int currentPage) throws Exception{
		int recordTotalCount = bdao.getArticleCount(); // 총 개시물의 개수
		int pageTotalCount = 0; // 전체 페이지의 개수

		if( recordTotalCount % Configuration.recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / Configuration.recordCountPerPage +1;
		}else {
			pageTotalCount = recordTotalCount / Configuration.recordCountPerPage;
		}

		if(currentPage < 1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount){
			currentPage = pageTotalCount;
		}

		int startNav = (currentPage-1)/Configuration.navCountPerPage * Configuration.navCountPerPage + 1;
		int endNav = startNav + Configuration.navCountPerPage - 1;
		if(endNav > pageTotalCount) {
			endNav = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if(startNav == 1) {
			needPrev = false;
		}
		if(endNav == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder("<nav aria-label='Page navigation'><ul class='pagination justify-content-center'>");
		
		if(needPrev) {
			sb.append("<li class='page-item'><a class='page-link' href='list?cpage="+(startNav-1)+"' id='prevPage' tabindex='-1' aria-disabled='true'>Previous</a></li>");
		}

		for(int i=startNav; i<=endNav; i++) {
			if(currentPage == i) {
				sb.append("<li class='page-item active' aria-current='page'><a class='page-link' href='list?cpage="+i+"'>"+i+"<span class=sr-only>(current)</span></a></li>");
				//sb.append("<li class='page-item active' aria-current='page'>"+i+"<span class='sr-only'>(current)</span></li>");
			}else {
				sb.append("<li class='page-item'><a class='page-link' href='list?cpage="+i+"'>"+i+"</a></li>");
			}
		}

		if(needNext) {
			sb.append("<li class=page-item><a class=page-link href='list?cpage="+(endNav+1)+"' id='nextPage'>다음</a></li> ");
		}		
		sb.append("</ul></nav>");
		return sb.toString();
	}
	
	public String navi (int cpage) throws Exception{
		String navi = this.getPageNav(cpage);
		return navi;
	}
	
	@Transactional("txManager") // 같은 DBCP를 사용하는 2개의 메서드가 원자성을 띄게 된다. (하나만 성공하는거 없고, 둘다 성공하거나, 둘다 실패하거나)
	public BoardDTO selectBySeq(int seq) throws Exception {
		int result = bdao.view_count(seq); // 조회수 +1
		BoardDTO dto = bdao.selectBySeq(seq); // 읽어오기
		return dto;
	}
	
	public int delete(String seq) throws Exception{
		int result=bdao.delete(seq);
		return result;
	}
	
	public int update(BoardDTO dto) throws Exception{
		int result= bdao.update(dto);
		return result;
	}
	
}
