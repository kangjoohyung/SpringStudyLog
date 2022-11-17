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
@ToString(exclude = {"salgrade" ,"dept" })
public class EmpDTO {
	private int empNo; //
	private String ename;
	private String job;
	private int mgr;
	private String hireDate;
	private int sal;
	private int comm;
	private int deptNo;
	
	//1 : 1인경우 : 1-1 번문제 ( 사원을 기준으로 부서정보함께 검색할때)
	private DeptDTO dept; //xml의 property="dept" javaType="deptDTO"
	
	// 1 : 1 
	private SalgradeDTO salgrade;


	
	

}//EmpDTO






