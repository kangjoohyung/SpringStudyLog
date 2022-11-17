package kosta.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Commentb {
	private int commentNo;
	private String userId;
	private String commentContent;
	private String regDate;
	
	//1:1인 경우
	private UserDTO users; //userDTO가 나중에 헷갈려질수있어서 users로 선언
	//property
	
	//1:다인 경우
	private List<ReplyDTO> replies;//property
}
