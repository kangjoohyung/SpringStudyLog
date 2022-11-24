package kosta.mvc.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;


@Setter //셋겟 없이 롬복으로 자동으로 쓰기, 
@Getter //컨스트럭터도 가능
public class UploadDTO {
	private String name;
	private MultipartFile file; //<input type="file" name="file"  -> name과 같아야함
	
	private String fileName;
	private long fileSize;
}
