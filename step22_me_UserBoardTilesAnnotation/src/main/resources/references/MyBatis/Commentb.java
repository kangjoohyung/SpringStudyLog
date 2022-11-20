package references.MyBatis;

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
	
	//1:1?�� 경우
	private UserDTO users; //userDTO�? ?��중에 ?��갈려질수?��?��?�� users�? ?��?��
	//property
	
	//1:?��?�� 경우
	private List<ReplyDTO> replies;//property
}
