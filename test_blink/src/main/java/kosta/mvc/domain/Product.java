package kosta.mvc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {

	@Id
	@Column(length = 30)
	private String productCode; 
	
	@OneToOne(mappedBy = "Goods")
	private Goods goods; //굿즈상품
}
