package kosta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
	private int replyNo;
	private String commentNo;
	private String userId;
	private String replyContent;
	private String regDate;
}
