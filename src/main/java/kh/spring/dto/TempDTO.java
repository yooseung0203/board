package kh.spring.dto;

import org.springframework.web.multipart.MultipartFile;

public class TempDTO {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public TempDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TempDTO(MultipartFile file) {
		super();
		this.file = file;
	}
}
