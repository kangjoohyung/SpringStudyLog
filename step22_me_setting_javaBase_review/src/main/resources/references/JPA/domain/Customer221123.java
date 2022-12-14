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

@Entity //ddl?΅??΄ true?Ό? ??΄λΈ? ??±
//@Table(name="aa") //table?΄λ¦? λ°κΏ? ??±κ°??₯
@AllArgsConstructor
@NoArgsConstructor
@Builder //lombokκΈ°λ₯
@ToString
@Setter
@Getter
public class Customer221123 {

	//strategy -> AUTOλ₯? ? κ°?μ§??? ?΄?€λ©? κ°μ? ????€λ₯? κ³΅μ ?΄? ?¬?©?κ²λ¨
	//κ·Έκ² ?«?€λ©? sequence λ§λ€λ©? ????€ λ³κ°λ‘? ??λ§? ?¬?©
	
	@Id //pk 
	@GeneratedValue(strategy = GenerationType.AUTO) //?λ³μλ₯? κ·μΉ(?΅?)? ?°?Ό ?¬?©, AUTO:???Όλ‘? ?€κ°? ??±??€
	private Long id;
	
	@Column(nullable=false, length=100) // not null, κΈΈμ΄? 100κ°?, length? 100 charλ‘? ??±
	//@Column(nullable=false, length=100, name="user_name") // nameμ§?? ??¬ ??±κ°??₯
	private String userName;
	
	@Column(nullable=true) //μ§?? ?λ©? int? nullκ°??₯
	private int age; //int κΈ°λ³Έ not null
	private String addr; //String κΈ°λ³Έ null
	
	@CreationTimestamp //insertκ°? ?  ? ???Όλ‘? ??¬ ? μ§μ? ?κ°μ ?€?  -> String λΆκ??₯ / LocalDate, LocalDateTime ?¬?©κ°??₯
	private LocalDateTime insertDate; //LocalDateTime? java 1.8?? μΆκ?? ??? - db? ? ?Έ?? ????? timestamp
	
	@UpdateTimestamp
	private LocalDateTime updateDate; //?±λ‘λ ?? ?΄κΈ°λλ¬Έμ ?±λ‘ν ?? ? μ§? ?? ₯?¨
	
	@Temporal(TemporalType.DATE) //@Temporal? ? ?Έ?? ? ?λ°μ Date, Calendarλ§? ? ?Έ κ°??₯
	private Date birthDay;
}
