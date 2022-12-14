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
//@ToString //??μ°Έμ‘° μ£Όμ
public class Member221124 {
	@Id//pk
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_pk")
	@SequenceGenerator(name="member_pk", allocationSize=1, sequenceName="member_pk")
	private Long memberId;
	
	private String name;
	private int age;
	
	/**
	 * 1. μ¦μ λ‘λ© : λ°λ‘ μ‘°μΈ - fetch=FetchType.EAGER
	 *              @OneToOne (1:1κ΄?κ³?), @ManyToOne (?€??1)  ??΄ μ¦μλ‘λ© κΈ°λ³Έ ?€? 
	 *              
	 * 2. μ§??° λ‘λ© : ???  ? μ‘°μΈ - fetch=FetchType.LAZY -> κΆμ₯, μ‘°μΈ?μ§?λ§? ? λ³΄λ κ°?μ§?κ³ μ΄
	 *              @OneToMany κΈ°λ³Έ
	 */
	@ManyToOne //?€:1 //λ¬΄μ‘°κ±? μ‘°μΈ
//	@ManyToOne(fetch=FetchType.LAZY) //?΄κ±? ??Όλ©? λ¬΄μ‘°κ±? μ‘°μΈ, κ·Έλ¦¬κ³? ??Όλ©? μ‘°μΈ?μ§?λ§? ? λ³΄λ?? κ°?μ§?κ³ μ΄
	@JoinColumn(name="tid") //??? ?΄λ¦μΌλ‘? λ°κ?? ?? κΈ°λ₯, ??Όλ©? ???Όλ‘? ??΄λΈμ΄λ¦?_pk(???)
	private Team221124 team; //ToString μ£Όμ - ??μ°Έμ‘° μ£Όμ 
	//???? fk ?€?  ??¬ ??΄λΈμ΄ ?? κ΄?κ³?
}
