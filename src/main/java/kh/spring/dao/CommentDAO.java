package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.CommentDTO;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(CommentDTO dto) {
		return mybatis.insert("Comment.insert",dto);
	}
	
	public List<CommentDTO> selectByParentSeq(int parent_seq) throws Exception{
		return mybatis.selectList("Comment.selectByParentSeq",parent_seq);
	}
}
