package kosta.mvc.controller;

/**
 * 현재 요청이 어떤 Controller를 실행해야 하는지 찾아주는 클래스이다
 *
 */
public class HandlerMapping {
	private static HandlerMapping factory=new HandlerMapping();
	private HandlerMapping() {}
	
	public static HandlerMapping getFactory() {
		return factory;
	}
	public Controller getController(String key) { 
		Controller con=null;
		if(key.equals("insert")) {
			con=new InsertController();
		}else if(key.equals("select")) {
			con=new SelectController();
		}else if(key.equals("update")) {
			con=new UpdateController();
		}else if(key.equals("delete")) {
			con=new DeleteController();
		}
		
		return con;
	}
}
