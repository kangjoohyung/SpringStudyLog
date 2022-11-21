package kosta.mvc.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;


@Setter //�°� ���� �Һ����� �ڵ����� ����, 
@Getter //����Ʈ���͵� ����
public class UploadDTO {
	private String name;
	private MultipartFile file; //<input type="file" name="file"  -> name�� ���ƾ���
	
	private String fileName;
	private long fileSize;
}
