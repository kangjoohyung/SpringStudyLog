package kosta.mvc.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 주문 테이블
 * 참조 : Users-users_id
 * @author 강주형
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Orders_gen")
	@SequenceGenerator(name="Orders_gen", allocationSize=1, sequenceName="Orders_seq")
	private Long ordersNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="users_id")
	private Users usersId; //코드 합칠때 객체 주소 주의
	
	@Column(nullable=false, length = 40)
	private String ordersReceiverName;
	@Column(nullable=false, length = 11)
	private String ordersReceiverPhone;
	@Column(nullable=false, length = 250)
	private String ordersAddr;
	private int ordersZipcode; 
	@Column(nullable=false, length = 20)
	private String ordersStatus;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE) //값 생성시 new Date() 혹은 API에서 반환된 결제완료 시간으로 입력
	private Date ordersDate;
	
	//@OneToMany(mappedBy = "Orderdetails", fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "Orderdetails") //LAZY
	private List<Orderdetails> orderdetailsList;
}
