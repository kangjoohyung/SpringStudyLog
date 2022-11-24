package references.JPA.domain.join;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@ToString //?ˆœ?™˜ì°¸ì¡° ì£¼ì˜
public class Member221124 {
	@Id//pk
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_pk")
	@SequenceGenerator(name="member_pk", allocationSize=1, sequenceName="member_pk")
	private Long memberId;
	
	private String name;
	private int age;
	
	/**
	 * 1. ì¦‰ì‹œ ë¡œë”© : ë°”ë¡œ ì¡°ì¸ - fetch=FetchType.EAGER
	 *              @OneToOne (1:1ê´?ê³?), @ManyToOne (?‹¤??1)  ?‘˜?´ ì¦‰ì‹œë¡œë”© ê¸°ë³¸ ?„¤? •
	 *              
	 * 2. ì§??—° ë¡œë”© : ?•„?š”?•  ?•Œ ì¡°ì¸ - fetch=FetchType.LAZY -> ê¶Œìž¥, ì¡°ì¸?—†ì§?ë§? ? •ë³´ëŠ” ê°?ì§?ê³ ì˜´
	 *              @OneToMany ê¸°ë³¸
	 */
	@ManyToOne //?‹¤:1 //ë¬´ì¡°ê±? ì¡°ì¸
//	@ManyToOne(fetch=FetchType.LAZY) //?´ê±? ?—†?œ¼ë©? ë¬´ì¡°ê±? ì¡°ì¸, ê·¸ë¦¬ê³? ?žˆ?œ¼ë©? ì¡°ì¸?—†ì§?ë§? ? •ë³´ë?? ê°?ì§?ê³ ì˜´
	@JoinColumn(name="tid") //?›?•˜?Š” ?´ë¦„ìœ¼ë¡? ë°”ê??ˆ˜ ?žˆ?Š” ê¸°ëŠ¥, ?—†?œ¼ë©? ?ž?™?œ¼ë¡? ?…Œ?´ë¸”ì´ë¦?_pk(???‚˜)
	private Team221124 team; //ToString ì£¼ì˜ - ?ˆœ?™˜ì°¸ì¡° ì£¼ì˜ 
	//???ƒ?„ fk ?„¤? • ?˜„?ž¬ ?…Œ?´ë¸”ì´ ?ž?‹ ê´?ê³?
}
