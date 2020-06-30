package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;
import kh.spring.statics.Configuration;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int getNextVal() {
		return mybatis.selectOne("Board.getNextval");
	}

	public int insert(BoardDTO dto) throws Exception {
		return mybatis.insert("Board.insert",dto);
	}

	public List<BoardDTO> selectAll() throws Exception{
		return mybatis.selectList("Board.selectAll");
	}

	public List<BoardDTO> selectByPage(int cpage) throws Exception{
		int start = cpage*Configuration.recordCountPerPage - (Configuration.recordCountPerPage - 1);
		int end = start + (Configuration.recordCountPerPage - 1);
		
		Map<String,Integer> param = new HashMap<>();
		param.put("start",start);
		param.put("end",end);

		return mybatis.selectList("Board.selectByPage",param);
	}

	public int view_count(int seq) throws Exception{
		return mybatis.update("Board.view_count",seq);
	}

	public BoardDTO selectBySeq(int seq) throws Exception{
		return mybatis.selectOne("Board.selectBySeq",seq);
	}

	public int delete(String seq) throws Exception{
		return mybatis.delete(seq);
	}

	public int update(BoardDTO dto) throws Exception{
		 Map<String, String> param = new HashMap<>();
	      param.put("columnName1", "title");
	      param.put("changeValue1", dto.getTitle());
	      param.put("columnName2", "contents");
	      param.put("changeValue2", dto.getContents());
	      param.put("targetColumn", "seq");
	      param.put("targetValue", Integer.toString(dto.getSeq()));
	      
	      System.out.println(param.size());
	      
		return mybatis.update("Board.update",param);
	}

	public int getArticleCount() throws Exception{
		return mybatis.selectOne("Board.getArticleCount");

	}

	

}
