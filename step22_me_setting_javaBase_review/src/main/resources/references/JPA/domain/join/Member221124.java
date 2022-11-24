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
//@ToString //?��?��참조 주의
public class Member221124 {
	@Id//pk
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_pk")
	@SequenceGenerator(name="member_pk", allocationSize=1, sequenceName="member_pk")
	private Long memberId;
	
	private String name;
	private int age;
	
	/**
	 * 1. 즉시 로딩 : 바로 조인 - fetch=FetchType.EAGER
	 *              @OneToOne (1:1�?�?), @ManyToOne (?��??1)  ?��?�� 즉시로딩 기본 ?��?��
	 *              
	 * 2. �??�� 로딩 : ?��?��?�� ?�� 조인 - fetch=FetchType.LAZY -> 권장, 조인?���?�? ?��보는 �?�?고옴
	 *              @OneToMany 기본
	 */
	@ManyToOne //?��:1 //무조�? 조인
//	@ManyToOne(fetch=FetchType.LAZY) //?���? ?��?���? 무조�? 조인, 그리�? ?��?���? 조인?���?�? ?��보�?? �?�?고옴
	@JoinColumn(name="tid") //?��?��?�� ?��름으�? 바�??�� ?��?�� 기능, ?��?���? ?��?��?���? ?��?��블이�?_pk(???��)
	private Team221124 team; //ToString 주의 - ?��?��참조 주의 
	//???��?�� fk ?��?�� ?��?�� ?��?��블이 ?��?�� �?�?
}
