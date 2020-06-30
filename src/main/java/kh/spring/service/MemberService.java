package kh.spring.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;

@Service
public class MemberService { // 회원과 관련된 Business Logic
	@Autowired
	private MemberDAO mdao;
	
	public static String getSHA512(String input){

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}
	public int insert(MemberDTO dto) throws Exception{
		dto.setPw(MemberService.getSHA512(dto.getPw()));
		int result= mdao.insert(dto);
		return result;
	}
	
	public boolean duplCheck(String id) throws Exception {
		boolean result = mdao.duplCheck(id);
		return result;
	}
	
	public boolean loginCheck(String id, String pw) {
		String pw_sha = getSHA512(pw);
		boolean result = mdao.loginCheck(id, pw_sha);
		return result;
	}
	
	public MemberDTO selectById(String id) {
		MemberDTO info = mdao.selectById(id);
		return info;
	}
	
	public int update(MemberDTO dto) throws Exception{
		dto.setPw(getSHA512(dto.getPw()));
		int result=mdao.update(dto);
		return result;
	}
	
	public int delete(String id) throws Exception {
		int result=mdao.delete(id);
		return result;
	}
	
	
}
