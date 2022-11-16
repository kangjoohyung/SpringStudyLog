package sample03;

public class BookDAOImpl implements BookDAO {

	public BookDAOImpl() {
		super();
	}

	@Override
	public void insert(BookVo bookVo) {
		
		System.out.println("BookDAOImpl insert 호출됨!!!");
		System.out.println(bookVo);
	}

}
