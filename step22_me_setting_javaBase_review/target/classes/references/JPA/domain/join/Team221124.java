package references.JPA.domain.join;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@ToString //?ˆœ?™˜ì°¸ì¡° ì£¼ì˜
public class Team221124 {
	/**
	 * @GeneratedValue ?Š” ??™?œ¼ë¡? ê°’ì„ ?„£?–´ì¤??‹¤.
	 *  : strategy?Š” persistence providerê°? ?—”?‹°?‹°?˜ ê¸°ë³¸?‚¤ë¥? ?ƒ?„±?• ?•Œ ?‚¬?š©?•´?•¼?•˜?Š” ì£¼í‚¤ ?ƒ?„± ? „?µ?„ ?œ»?•¨ (ê¸°ë³¸ AUTO)
	 *     
	 *  1) GenerationType.AUTO
	 *      : ?°?´?„°ë² ì´?? ë²¤ë”?— ì¢…ì†? ?´ì§? ?•Šê³? hibernate ?‚´ë¶??—?„œ ê¸°ë³¸?‚¤ë¥? ?ƒ?„±?•´?„œ ?• ?‹¹?•´ì£¼ëŠ” ë°©ë²• 
	 *      : Oracle - SEQUENCE , MYSQL -AUTOINCREMENT , MSSQL  - IDENTITY
	 *       
	 *  2) GenerationType.SEQUENCE
	 *       : ORACLE ?˜ ?‹œ???Š¤ ê°œë…?„ ?‚¬?š©?• ?•Œ 
	 *       : ?´ ? „?µ?„ ?“¸?•Œ?Š” ë°˜ë“œ?‹œ   @SequenceGenerator ?•¨ê»? ?‚¬?š©
	 *  
	 *  3) GenerationType.TABLE
	 *      : ?‚¤ë¥? ?ƒ?„±?• ?•Œ ?…Œ?´ë¸”ì´ ì§ì ‘ ë§Œë“¤?–´?„œ ?‚¬?š©?•˜?Š” ë°©ë²• 
	 *  
	 *  4) GenerationType.IDENTITY
	 *       : MS-SQL, MY-SQLë¥? ?‚¬?š©?• ?•Œ 
	 * */
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator="team_pk" )
	@SequenceGenerator(name="team_pk", allocationSize=1, sequenceName = "team_sq")
	private Long id;
	private String teamName;
	
	//?•œ ???— ?†Œ?†?œ ?šŒ?› ? •ë³? ê²??ƒ‰
	/**
	 * mappedBy?Š” ë¶?ëª¨í‚¤ ìª½ì— ?„¤? •?•œ?‹¤.
	 * (ë¶?ëª¨ë?? ì¤‘ì‹¬?œ¼ë¡? ??‹ ? •ë³´ë?? ê²??ƒ‰?•  ?•Œ ì¡°ì¸ ???ƒ?„ ?•Œ? ¤ì¤˜ì•¼ ?•œ?‹¤)
	 */
//	@OneToMany(mappedBy="team") //ì§??—°ë¡œë”© , mappedBy?´ë¦„ì? ?•´?‹¹ ?´?˜?Š¤?˜ ê°ì²´?´ë¦„ìœ¼ë¡? ?…? ¥
	@OneToMany(mappedBy="team", fetch=FetchType.EAGER)//ì¦‰ì‹œë¡œë”©-ì¡°ì¸
	private List<Member221124> memberList;//ToString ì£¼ì˜-?ˆœ?™˜ì°¸ì¡° ë°œìƒ 
	//?–‘ë°©í–¥ ?„¤? •?• ?•Œ ë¶?ëª¨ìª½?—?Š” mappedByê°? ?ˆ?–´?•¼?•œ?‹¤.(ë¶?ëª¨ìª½?—?„œ?Š” FK?„¤? •?„ ?•Œ ?ˆ˜ ?—†ê¸? ?•Œë¬¸ì—)
}
