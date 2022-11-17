package kosta.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalgradeDTO {
	private int grade;
	private int losal;
	private int hisal;
	
	private List<EmpDTO> empList;
	
}//DeptDTO