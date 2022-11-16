package sample09;

import javax.annotation.Resource;

public class ResourceBean {
	
	//라이브러리 등록해야만 @Resource 사용 가능
	@Resource(name="empDTO")//xml의 id의 이름과 name이 같은 객체를 찾아서 주입
	private EmpDTO emp1;
	
	@Resource(name="dto2")
	private EmpDTO emp2;
	
	@Resource(name="service")
	private EmpService service;
	
	@Resource(name="controller")
	private EmpController controller;
	
	public void test() {
		System.out.println("emp1="+emp1+", name="+emp1.getName());
		System.out.println("emp2="+emp2+", name="+emp2.getName());
		System.out.println("service="+service);
		System.out.println("controller="+controller);
	}
}
