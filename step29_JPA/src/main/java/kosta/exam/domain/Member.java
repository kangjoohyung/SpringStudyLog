package kosta.exam.domain;

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
//@ToString //순환참조 주의
public class Member {
	@Id//pk
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_pk")
	@SequenceGenerator(name="member_pk", allocationSize=1, sequenceName="member_pk")
	private Long memberId;
	
	private String name;
	private int age;
	
	/**
	 * 1. 즉시 로딩 : 바로 조인 - fetch=FetchType.EAGER
	 *              @OneToOne (1:1관계), @ManyToOne (다대1)  둘이 즉시로딩 기본 설정
	 *              
	 * 2. 지연 로딩 : 필요할 때 조인 - fetch=FetchType.LAZY -> 권장, 조인없지만 정보는 가지고옴
	 *              @OneToMany 기본
	 */
	@ManyToOne //다:1 //무조건 조인
//	@ManyToOne(fetch=FetchType.LAZY) //이거 없으면 무조건 조인, 그리고 있으면 조인없지만 정보를 가지고옴
	@JoinColumn(name="tid") //원하는 이름으로 바꿀수 있는 기능, 없으면 자동으로 테이블이름_pk(였나)
	private Team team; //ToString 주의 - 순환참조 주의 
	//대상을 fk 설정 현재 테이블이 자식 관계
}
