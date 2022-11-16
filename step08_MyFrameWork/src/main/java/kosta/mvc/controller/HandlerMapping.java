package kosta.mvc.controller;

/**
 * ���� ��û�� � Controller�� �����ؾ� �ϴ��� ã���ִ� Ŭ�����̴�
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
