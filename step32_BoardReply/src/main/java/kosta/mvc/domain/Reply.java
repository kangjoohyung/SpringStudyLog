package kosta.mvc.domain;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_rno_gen")
	@SequenceGenerator(name="reply_rno_gen", allocationSize = 1, sequenceName = "reply_rno_seq")
	private Long rno; //댓글 번호
	
	private String content; //댓글 내용
	
	@CreationTimestamp
	private LocalDateTime insertDate;
	
	@ManyToOne(fetch = FetchType.LAZY) //default 즉시로딩, 지연로딩 권장
	@JoinColumn(name="free_bno")//컬럼명 지정
	FreeBoard freeBoard;
}
