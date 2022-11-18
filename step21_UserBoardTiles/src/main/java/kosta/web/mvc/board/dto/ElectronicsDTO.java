package kosta.web.mvc.board.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ElectronicsDTO {
	
	private String modelNum;
	private String modelName;
	private int price;
	private String description;
	private String password;
	
	private String writeday;
	private int readnum;
	
	private String fname; //등록할때 전송되는게 아니고 직접 넣어줘야한다.
	private long fsize;//등록할때 전송되는게 아니고 직접 넣어줘야한다.
	
	private MultipartFile file; // <input type="file" name="file"
	

}
