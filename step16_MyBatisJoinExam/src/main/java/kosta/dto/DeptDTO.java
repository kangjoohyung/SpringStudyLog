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
@ToString//(exclude = {"dname"})
public class DeptDTO {
	private int deptNo;
	private String dname;
	private String loc;
	
	
	//1 : 다
	private List<EmpDTO> empList; // xml에서  porperty="empList" ofType="empDTO"


	
	
	

}//DeptDTO

