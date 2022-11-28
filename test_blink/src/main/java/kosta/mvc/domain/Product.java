package kosta.mvc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Product {

	@Id
	@Column(length = 30)
	private String productCode; 
	
	private int productStock;
	
	@OneToOne(mappedBy = "Goods")
	private Goods goods; //굿즈상품
}
