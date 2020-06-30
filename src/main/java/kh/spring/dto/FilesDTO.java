package kh.spring.dto;

import org.springframework.web.multipart.MultipartFile;

public class FilesDTO {
	private MultipartFile[] files;

	public FilesDTO() {
		super();
	}
	public FilesDTO(MultipartFile[] files) {
		this.files = files;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}		
}
