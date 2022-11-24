package kosta.exam.domain;

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

@Entity //ddl옵션이 true일때 테이블 생성
//@Table(name="aa") //table이름 바꿔서 생성가능
@AllArgsConstructor
@NoArgsConstructor
@Builder //lombok기능
@ToString
@Setter
@Getter
public class Customer {

	//strategy -> AUTO를 두 가지에서 쓴다면 같은 시퀀스를 공유해서 사용하게됨
	//그게 싫다면 sequence 만들면 시퀀스 별개로 하나만 사용
	
	@Id //pk 
	@GeneratedValue(strategy = GenerationType.AUTO) //식별자를 규칙(옵션)에 따라 사용, AUTO:자동으로 키가 생성된다
	private Long id;
	
	@Column(nullable=false, length=100) // not null, 길이는 100개, length는 100 char로 생성
	//@Column(nullable=false, length=100, name="user_name") // name지정하여 생성가능
	private String userName;
	
	@Column(nullable=true) //지정하면 int도 null가능
	private int age; //int 기본 not null
	private String addr; //String 기본 null
	
	@CreationTimestamp //insert가 될 때 자동으로 현재 날짜와 시간을 설정 -> String 불가능 / LocalDate, LocalDateTime 사용가능
	private LocalDateTime insertDate; //LocalDateTime는 java 1.8에서 추가된 타입 - db에 선언되는 타입은 timestamp
	
	@UpdateTimestamp
	private LocalDateTime updateDate; //등록도 수정이기때문에 등록할때도 날짜 입력됨
	
	@Temporal(TemporalType.DATE) //@Temporal을 선언했을 때 자바의 Date, Calendar만 선언 가능
	private Date birthDay;
}
