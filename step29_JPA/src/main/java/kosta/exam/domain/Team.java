package kosta.exam.domain;

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
@ToString //순환참조 주의
public class Team {
	/**
	 * @GeneratedValue 는 자동으로 값을 넣어준다.
	 *  : strategy는 persistence provider가 엔티티의 기본키를 생성할때 사용해야하는 주키 생성 전략을 뜻함 (기본 AUTO)
	 *     
	 *  1) GenerationType.AUTO
	 *      : 데이터베이싀 벤더에 종속적이지 않고 hibernate 내부에서 기본키를 생성해서 할당해주는 방법 
	 *      : Oracle - SEQUENCE , MYSQL -AUTOINCREMENT , MSSQL  - IDENTITY
	 *       
	 *  2) GenerationType.SEQUENCE
	 *       : ORACLE 의 시퀀스 개념을 사용할때 
	 *       : 이 전략을 쓸때는 반드시   @SequenceGenerator 함께 사용
	 *  
	 *  3) GenerationType.TABLE
	 *      : 키를 생성할때 테이블이 직접 만들어서 사용하는 방법 
	 *  
	 *  4) GenerationType.IDENTITY
	 *       : MS-SQL, MY-SQL를 사용할때 
	 * */
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE, generator="team_pk" )
	@SequenceGenerator(name="team_pk", allocationSize=1, sequenceName = "team_sq")
	private Long id;
	private String teamName;
	
	//한 팀에 소속된 회원 정보 검색
	/**
	 * mappedBy는 부모키 쪽에 설정한다.
	 * (부모를 중심으로 자식 정보를 검색할 때 조인 대상을 알려줘야 한다)
	 */
//	@OneToMany(mappedBy="team") //지연로딩 , mappedBy이름은 해당 클래스의 객체이름으로 입력
	@OneToMany(mappedBy="team", fetch=FetchType.EAGER)//즉시로딩-조인
	private List<Member> memberList;//ToString 주의-순환참조 발생 
	//양방향 설정할때 부모쪽에는 mappedBy가 있어야한다.(부모쪽에서는 FK설정을 알 수 없기 때문에)
}
