package kosta.main;

import kosta.dao.EmpDAO;
import kosta.dto.EmpDTO;
import kosta.dto.StudentDTO;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("1. 사원 이름 검색-------------");
		EmpDAO dao=new EmpDAO();
		//dao.selectName();

//		System.out.println("\n2. 사원 등록하기(insert)-------");
//		dao.insert(new EmpDTO(8001, "나미", "백조", 2500, null))

//		System.out.println("\n3. 사원번호로 사원정보 업데이트");
//		dao.update(new EmpDTO(8001, "나미2", "100조", 3000, null));//인수 맞춰서 넣을필요없이 그냥 null로 sql에 안쓰는 것 넣으면 됨

//		System.out.println("\n4. 사원번호로 사원정보 삭제하기");
//		dao.delete(8001);
	
//		System.out.println("\n5. 전체검색--------------");
//		dao.selectAll();
//		
//		System.out.println("\n6. 사원번호로 사원 검색-----");
//		dao.selectByEmpno(7900);

//		System.out.println("\n7. 입력된 숫자의급여보다 적게 받는 사원 정보 검색-----");
//		dao.selectLessThanSal(1500);
		
//		System.out.println("\n8. 입력된 숫자의급여범위에 들어가는 사원 정보 검색-----");
//		dao.selectMinAndMax(2000, 3000);
		
//		System.out.println("\n9. 입력된 String(컬럼명)으로 정렬하기-----");
//		dao.selectOrder("job");//#{~}preparedStatement방식에선 사용 불가능하다. (statement말고는 방법없나?)
		
//		System.out.println("\n10. 조건 여러개 입력받아 검색하기-----");
//		dao.selectWhereMix("ename", "a", 8000, 2000); //ename칸에는 ${}로 사용
//		//where upper (${keyField}) like '%'||#{keyword}||'%' or empno=#{emp.empno} or sal> #{emp.sal}
//	
//		System.out.println("\n11. job에 해당하는 사원정보 검색-----");
//		dao.selectByJob("MANAGER");//대문자만 가능("manager")하면 검색 안나옴-왜?
		
//		System.out.println("\n12. Student 전체검색----------");
//		dao.studentSelectAll();
//		
//		System.out.println("\n13. Student 등록하기----------");
//		dao.studentInsert(new StudentDTO(0, "도윤", "9리", "015"));
		
		System.out.println("\n14. Student null 등록하기----------");
		dao.studentInsert(new StudentDTO(0, "도윤", "9리", null));
	}

}
