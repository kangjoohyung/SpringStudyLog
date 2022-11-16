package sample03;


public class BookController {

	BookDAO dao=null;
	BookVo vo=null;
	public BookController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookController(BookDAO dao, BookVo vo) {
		super();
		this.dao = dao;
		this.vo = vo;
	}
	
	
}
