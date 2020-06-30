package kh.spring.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentDTO {

	private int seq;
	private String writer;
	private String contents;
	private Timestamp write_date;
	private int parent_seq;
private String sDate;
	
	public String getsDate() {
		long write_date = this.write_date.getTime(); // 글 작성 시점
		long current_date = System.currentTimeMillis(); // 현재 시점
		
		long gapTime = (current_date - write_date)/1000; //밀리(1000)세컨드니까 1000으로 나눠야 초(sec)가 나옴
		
		
		if(gapTime<60) { //글이 작성된 갭타임이 60초보다 작은 경우
			return "방금전";
		}else if(gapTime < 300) {
			return "5분 이내";
		}else if(gapTime < 3600) {
			return "1시간 이내";
		}else if(gapTime < 86400) {
			return "24시간 이내";
		}else {
			return sDate;
		}
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public int getParent_seq() {
		return parent_seq;
	}
	public void setParent_seq(int parnet_seq) {
		this.parent_seq = parnet_seq;
	}
	public CommentDTO(int seq, String writer, String contents, Timestamp write_date, int parnet_seq) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.write_date = write_date;
		this.parent_seq = parnet_seq;
		this.sDate = new SimpleDateFormat("YYYY-MM-DD").format(write_date);
	}
	
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
