package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	// 이렇게 보통 3개의 메서드를 만들어서 쓰게 된다.
	// insert / update / delete  (3개의 기능 묶음)
	// select - DTO 
	// select - List<DTO>
	public MemberDTO selectById(String id) {
		return mybatis.selectOne("Member.selectById",id);
	}
	
	public boolean loginCheck(String id, String pw) {
		Map<String,String> param = new HashMap<>();
		param.put("id",id);
		param.put("pw",pw);
		int result = mybatis.selectOne("Member.loginCheck",param);
		if(result > 0) return true;
		return false;
	}

	public boolean duplCheck(String id) throws Exception {
		int result=mybatis.selectOne("Member.duplCheck",id);
		if(result>0) return true;
		return false;
	}
	
	public int update(MemberDTO dto) throws Exception{
		return mybatis.update("Member.update",dto);
	}

	public int insert(MemberDTO dto) throws Exception{
		return mybatis.insert("Member.insert",dto);
	}

	public int delete(String id) throws Exception{
		return mybatis.delete("Member.delete",id);
	}
	
	

	//리스트로 받는거와 하나만 뽑아내는것의 차이점은 list는 query로만 쓴다는것이고
	// 하나만 뽑는것은 queryForObject를 쓴다는 것이다. 또한 리턴값이 다른것도.
	
	
	
//	public MemberDTO selectById(MemberDTO dto) {
//		String sql = "select * from member where id= ? and pw=?";
//		return jdbc.queryForObject(sql, new Object[] {dto.getId()}, new RowMapper<MemberDTO>() {
//			@Override
//			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				MemberDTO dto = new MemberDTO();
//				
//				dto.setId(rs.getString("id"));
//				dto.setPw(rs.getString("pw"));
//				dto.setName(rs.getString("name"));
//				dto.setEmail(rs.getString("email"));
//				dto.setZipcode(rs.getString("zipcode"));
//				dto.setAddress1(rs.getString("address1"));
//				dto.setAddress2(rs.getString("address2"));
//				return dto;
//			}
//		});
//	}
	
}