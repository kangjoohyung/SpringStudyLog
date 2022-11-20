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
	
	//1:1?¸ ê²½ìš°
	private UserDTO users; //userDTOê°? ?‚˜ì¤‘ì— ?—·ê°ˆë ¤ì§ˆìˆ˜?ˆ?–´?„œ usersë¡? ?„ ?–¸
	//property
	
	//1:?‹¤?¸ ê²½ìš°
	private List<ReplyDTO> replies;//property
}
