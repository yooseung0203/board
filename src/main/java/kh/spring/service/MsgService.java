package kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.MsgDAO;
import kh.spring.dto.MsgDTO;

@Service
public class MsgService {
	@Autowired
	private MsgDAO msgdao;
	
	//받은쪽지함
	public List<MsgDTO> selectBySender(String msg_receiver) throws Exception{
		List<MsgDTO> dto = msgdao.selectBySender(msg_receiver);
		return dto;
	}
	//보낸쪽지함
	public List<MsgDTO> selectByReceiver(String msg_receiver) throws Exception{
		List<MsgDTO> dto = msgdao.selectByReceiver(msg_receiver);
		return dto;
	}
	//관리자
	public List<MsgDTO> selectByAdmin(String msg_receiver) throws Exception{
		List<MsgDTO> dto = msgdao.selectByAdmin(msg_receiver);
		return dto;
	}
	
	public int insert(MsgDTO msgdto)throws Exception {
		int result = msgdao.insert(msgdto);
		return result;
	}
	
	public int insertWelcome(String msg_receiver)throws Exception{
		int result = msgdao.insertWelcome(msg_receiver);
		return result;
	}
	
}
