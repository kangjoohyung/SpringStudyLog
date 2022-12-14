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
@ToString //??μ°Έμ‘° μ£Όμ
public class Team221124 {
	/**
	 * @GeneratedValue ? ???Όλ‘? κ°μ ?£?΄μ€??€.
	 *  : strategy? persistence providerκ°? ??°?°? κΈ°λ³Έ?€λ₯? ??±? ? ?¬?©?΄?Ό?? μ£Όν€ ??± ? ?΅? ?»?¨ (κΈ°λ³Έ AUTO)
	 *     
	 *  1) GenerationType.AUTO
	 *      : ?°?΄?°λ² μ΄?? λ²€λ? μ’μ? ?΄μ§? ?κ³? hibernate ?΄λΆ??? κΈ°λ³Έ?€λ₯? ??±?΄? ? ?Ή?΄μ£Όλ λ°©λ² 
	 *      : Oracle - SEQUENCE , MYSQL -AUTOINCREMENT , MSSQL  - IDENTITY
	 *       
	 *  2) GenerationType.SEQUENCE
	 *       : ORACLE ? ????€ κ°λ? ?¬?©? ? 
	 *       : ?΄ ? ?΅? ?Έ?? λ°λ?   @SequenceGenerator ?¨κ»? ?¬?©
	 *  
	 *  3) GenerationType.TABLE
	 *      : ?€λ₯? ??±? ? ??΄λΈμ΄ μ§μ  λ§λ€?΄? ?¬?©?? λ°©λ² 
	 *  
	 *  4) GenerationType.IDENTITY
	 *       : MS-SQL, MY-SQLλ₯? ?¬?©? ? 
	 * */
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator="team_pk" )
	@SequenceGenerator(name="team_pk", allocationSize=1, sequenceName = "team_sq")
	private Long id;
	private String teamName;
	
	//? ??? ??? ?? ? λ³? κ²??
	/**
	 * mappedBy? λΆ?λͺ¨ν€ μͺ½μ ?€? ??€.
	 * (λΆ?λͺ¨λ?? μ€μ¬?Όλ‘? ?? ? λ³΄λ?? κ²???  ? μ‘°μΈ ???? ?? €μ€μΌ ??€)
	 */
//	@OneToMany(mappedBy="team") //μ§??°λ‘λ© , mappedBy?΄λ¦μ? ?΄?Ή ?΄??€? κ°μ²΄?΄λ¦μΌλ‘? ?? ₯
	@OneToMany(mappedBy="team", fetch=FetchType.EAGER)//μ¦μλ‘λ©-μ‘°μΈ
	private List<Member221124> memberList;//ToString μ£Όμ-??μ°Έμ‘° λ°μ 
	//?λ°©ν₯ ?€? ? ? λΆ?λͺ¨μͺ½?? mappedByκ°? ??΄?Ό??€.(λΆ?λͺ¨μͺ½??? FK?€? ? ? ? ?κΈ? ?λ¬Έμ)
}
