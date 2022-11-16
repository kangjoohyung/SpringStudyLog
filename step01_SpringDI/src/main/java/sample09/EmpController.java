package sample09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class EmpController {
	
	@Autowired // byType우선 주입 -> byBName주입 (생성자 만들필요 없어짐)
	private EmpDTO empDTO;//주입
	
	@Autowired
	@Qualifier("aa") //이름을 임시방편으로 등록하는 방법-> ~~processor 또 등록해야함
	private EmpDTO empDTO2;
	
	@Autowired
	private EmpService empService;// 들어오는 값을 저장만 할때는 선언 이후 만들필요도 없어짐
	
	public void test() {
		System.out.println("empDTO="+empDTO+",empDTO.getName()"+empDTO.getName());
		System.out.println("empDTO2="+empDTO2+",empDTO2.getName()"+empDTO2.getName());
		System.out.println("empService="+empService);
		
		empService.test();
	}
}
