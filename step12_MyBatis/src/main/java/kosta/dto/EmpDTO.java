package kosta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {
	
	private int empno;
	private String empName; //일부러 empName으로 작성 -> DB에서는 컬럼명이 ename이다.
	private String job;
	private int sal;
	private String hiredate;
	
}
