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

@Entity //ddl?��?��?�� true?��?�� ?��?���? ?��?��
//@Table(name="aa") //table?���? 바꿔?�� ?��?���??��
@AllArgsConstructor
@NoArgsConstructor
@Builder //lombok기능
@ToString
@Setter
@Getter
public class Customer221123 {

	//strategy -> AUTO�? ?�� �?�??��?�� ?��?���? 같�? ?��???���? 공유?��?�� ?��?��?��게됨
	//그게 ?��?���? sequence 만들�? ?��???�� 별개�? ?��?���? ?��?��
	
	@Id //pk 
	@GeneratedValue(strategy = GenerationType.AUTO) //?��별자�? 규칙(?��?��)?�� ?��?�� ?��?��, AUTO:?��?��?���? ?���? ?��?��?��?��
	private Long id;
	
	@Column(nullable=false, length=100) // not null, 길이?�� 100�?, length?�� 100 char�? ?��?��
	//@Column(nullable=false, length=100, name="user_name") // name�??��?��?�� ?��?���??��
	private String userName;
	
	@Column(nullable=true) //�??��?���? int?�� null�??��
	private int age; //int 기본 not null
	private String addr; //String 기본 null
	
	@CreationTimestamp //insert�? ?�� ?�� ?��?��?���? ?��?�� ?��짜�? ?��간을 ?��?�� -> String 불�??�� / LocalDate, LocalDateTime ?��?���??��
	private LocalDateTime insertDate; //LocalDateTime?�� java 1.8?��?�� 추�??�� ???�� - db?�� ?��?��?��?�� ???��?? timestamp
	
	@UpdateTimestamp
	private LocalDateTime updateDate; //?��록도 ?��?��?��기때문에 ?��록할?��?�� ?���? ?��?��?��
	
	@Temporal(TemporalType.DATE) //@Temporal?�� ?��?��?��?�� ?�� ?��바의 Date, Calendar�? ?��?�� �??��
	private Date birthDay;
}
