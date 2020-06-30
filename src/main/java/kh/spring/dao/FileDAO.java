package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.FileDTO;

@Repository
public class FileDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(FileDTO dto) {
		return mybatis.insert("File.insert",dto);
	}
	
	public FileDTO getFileBySeq(int seq) {
		return mybatis.selectOne("File.getFileBySeq",seq);
	}
	
	public List<FileDTO> getFileList(int seq) {
		return mybatis.selectList("File.getFileList",seq);
	}
	
//	public List<FileDTO> getFileList() { // 파일저장리스트 전체
//		String sql = "select * from files";
//		return jdbc.query(sql, new RowMapper<FileDTO>() {
//			@Override
//			public FileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				FileDTO dto = new FileDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setOriFileName(rs.getString("oriname"));
//				dto.setSysFileName(rs.getString("sysname"));
//				dto.setParentSeq(rs.getInt("parent_seq"));
//				return dto;
//			}
//		});
//	}
//	

}
