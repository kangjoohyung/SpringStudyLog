package kosta.mvc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 주문 상세 테이블
 * 참조 : Orders-orders_no, Product-product_code
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Orderdetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Orderdetails_gen")
	@SequenceGenerator(name="Orderdetails_gen", allocationSize = 1, sequenceName = "Orderdetails_seq")
	private Long orderdetailsNo;

	//@ManyToOne(fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="orders_no")
	private Orders ordersNo; //private String usersId; @Column(length = 20)
	
	//@ManyToOne(fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_code")
	private Product product; //private String productCode; @Column(length = 30)

	private int orderdetailsQty;
	private int orderdetailsPrice;
}
