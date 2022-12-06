package references.JPA.boot.step32;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name="테이블이름설정")
@Builder
@ToString
@RequiredArgsConstructor //nonnull적용시 필요, final사용시 필요
public class FreeBoard {

	@NonNull
	@Id //pk
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="free_bno_gen")
	@SequenceGenerator(name="free_bno_gen", sequenceName = "free_bno_seq", allocationSize = 1)
	private Long bno;
	private String subject;
	private String writer;
	
	@Column(length = 500)
	private String content;
	private String password;
	private int readnum;
	
	@CreationTimestamp
	private LocalDateTime insertDate;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;
	
	//댓글 정보
	@OneToMany(mappedBy = "freeBoard") //기본 지연로딩
	private List<Reply> replyList;
}
