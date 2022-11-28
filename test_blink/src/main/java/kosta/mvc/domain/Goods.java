package kosta.mvc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {

	@Id
	@Column(length = 30)
	@JoinColumn(name="product_code")
	@OneToOne(fetch = FetchType.LAZY)
	private String productCode;
	
	@Column(length = 1)//1=멤버쉽 전용, 기본값0
	private int goodsMembershiponly;
}
