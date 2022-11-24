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
@ToString //?��?��참조 주의
public class Team221124 {
	/**
	 * @GeneratedValue ?�� ?��?��?���? 값을 ?��?���??��.
	 *  : strategy?�� persistence provider�? ?��?��?��?�� 기본?���? ?��?��?��?�� ?��?��?��?��?��?�� 주키 ?��?�� ?��?��?�� ?��?�� (기본 AUTO)
	 *     
	 *  1) GenerationType.AUTO
	 *      : ?��?��?��베이?? 벤더?�� 종속?��?���? ?���? hibernate ?���??��?�� 기본?���? ?��?��?��?�� ?��?��?��주는 방법 
	 *      : Oracle - SEQUENCE , MYSQL -AUTOINCREMENT , MSSQL  - IDENTITY
	 *       
	 *  2) GenerationType.SEQUENCE
	 *       : ORACLE ?�� ?��???�� 개념?�� ?��?��?��?�� 
	 *       : ?�� ?��?��?�� ?��?��?�� 반드?��   @SequenceGenerator ?���? ?��?��
	 *  
	 *  3) GenerationType.TABLE
	 *      : ?���? ?��?��?��?�� ?��?��블이 직접 만들?��?�� ?��?��?��?�� 방법 
	 *  
	 *  4) GenerationType.IDENTITY
	 *       : MS-SQL, MY-SQL�? ?��?��?��?�� 
	 * */
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator="team_pk" )
	@SequenceGenerator(name="team_pk", allocationSize=1, sequenceName = "team_sq")
	private Long id;
	private String teamName;
	
	//?�� ???�� ?��?��?�� ?��?�� ?���? �??��
	/**
	 * mappedBy?�� �?모키 쪽에 ?��?��?��?��.
	 * (�?모�?? 중심?���? ?��?�� ?��보�?? �??��?�� ?�� 조인 ???��?�� ?��?��줘야 ?��?��)
	 */
//	@OneToMany(mappedBy="team") //�??��로딩 , mappedBy?��름�? ?��?�� ?��?��?��?�� 객체?��름으�? ?��?��
	@OneToMany(mappedBy="team", fetch=FetchType.EAGER)//즉시로딩-조인
	private List<Member221124> memberList;//ToString 주의-?��?��참조 발생 
	//?��방향 ?��?��?��?�� �?모쪽?��?�� mappedBy�? ?��?��?��?��?��.(�?모쪽?��?��?�� FK?��?��?�� ?�� ?�� ?���? ?��문에)
}
