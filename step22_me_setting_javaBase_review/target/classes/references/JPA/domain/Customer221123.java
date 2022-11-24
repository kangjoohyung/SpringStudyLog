package references.JPA.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity //ddl?˜µ?…˜?´ true?¼?•Œ ?…Œ?´ë¸? ?ƒ?„±
//@Table(name="aa") //table?´ë¦? ë°”ê¿”?„œ ?ƒ?„±ê°??Š¥
@AllArgsConstructor
@NoArgsConstructor
@Builder //lombokê¸°ëŠ¥
@ToString
@Setter
@Getter
public class Customer221123 {

	//strategy -> AUTOë¥? ?‘ ê°?ì§??—?„œ ?“´?‹¤ë©? ê°™ì? ?‹œ???Š¤ë¥? ê³µìœ ?•´?„œ ?‚¬?š©?•˜ê²Œë¨
	//ê·¸ê²Œ ?‹«?‹¤ë©? sequence ë§Œë“¤ë©? ?‹œ???Š¤ ë³„ê°œë¡? ?•˜?‚˜ë§? ?‚¬?š©
	
	@Id //pk 
	@GeneratedValue(strategy = GenerationType.AUTO) //?‹ë³„ìë¥? ê·œì¹™(?˜µ?…˜)?— ?”°?¼ ?‚¬?š©, AUTO:??™?œ¼ë¡? ?‚¤ê°? ?ƒ?„±?œ?‹¤
	private Long id;
	
	@Column(nullable=false, length=100) // not null, ê¸¸ì´?Š” 100ê°?, length?Š” 100 charë¡? ?ƒ?„±
	//@Column(nullable=false, length=100, name="user_name") // nameì§?? •?•˜?—¬ ?ƒ?„±ê°??Š¥
	private String userName;
	
	@Column(nullable=true) //ì§?? •?•˜ë©? int?„ nullê°??Š¥
	private int age; //int ê¸°ë³¸ not null
	private String addr; //String ê¸°ë³¸ null
	
	@CreationTimestamp //insertê°? ?  ?•Œ ??™?œ¼ë¡? ?˜„?¬ ?‚ ì§œì? ?‹œê°„ì„ ?„¤? • -> String ë¶ˆê??Š¥ / LocalDate, LocalDateTime ?‚¬?š©ê°??Š¥
	private LocalDateTime insertDate; //LocalDateTime?Š” java 1.8?—?„œ ì¶”ê??œ ???… - db?— ?„ ?–¸?˜?Š” ???…?? timestamp
	
	@UpdateTimestamp
	private LocalDateTime updateDate; //?“±ë¡ë„ ?ˆ˜? •?´ê¸°ë•Œë¬¸ì— ?“±ë¡í• ?•Œ?„ ?‚ ì§? ?…? ¥?¨
	
	@Temporal(TemporalType.DATE) //@Temporal?„ ?„ ?–¸?–ˆ?„ ?•Œ ?ë°”ì˜ Date, Calendarë§? ?„ ?–¸ ê°??Š¥
	private Date birthDay;
}
