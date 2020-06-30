package kh.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MsgDTO;

@Repository
public class MsgDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	//받은쪽지함
	public List<MsgDTO> selectBySender(String msg_receiver) throws Exception{
		System.out.println(msg_receiver);
		return mybatis.selectList("msg.selectBySender",msg_receiver);
	}
	//보낸쪽지함
	public List<MsgDTO> selectByReceiver(String msg_receiver) throws Exception{
		System.out.println(msg_receiver);
		return mybatis.selectList("msg.selectByReceiver",msg_receiver);
	}
	
	//관리자쪽지함
	public List<MsgDTO> selectByAdmin(String msg_receiver) throws Exception{
		System.out.println(msg_receiver);
		return mybatis.selectList("msg.selectByAdmin",msg_receiver);
	}
	//쪽지보내기
	public int insert(MsgDTO msgdto)throws Exception{
		return mybatis.insert("msg.insert",msgdto);
	}
	//회원가입축하
	public int insertWelcome(String msg_receiver)throws Exception{
		return mybatis.insert("msg.insertWelcome",msg_receiver);
	}
	
}
