package kosta.mvc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

	@Id
	@Column(length = 20)
	private String usersId;
	
	@Column(nullable = false)
	private int usersMembership; //0이면 일반, 1이면 멤버쉽
}
