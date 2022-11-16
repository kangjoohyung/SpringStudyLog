package sample08;

public class BookDAOImpl implements BookDAO {
	//<bean class="BookDAOImpl" autowire="constructor"
	private DbUtil dbUtil;

	public BookDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookDAOImpl(DbUtil dbUtil) {
		this.dbUtil=dbUtil;
	}
	
	@Override
	public void save(EmailSender emailSender, MessageSender messageSender, BookDTO book1, BookDTO book2) {
		// TODO Auto-generated method stub
		System.out.println("emailSender="+emailSender);
		System.out.println("messageSender="+messageSender);
		System.out.println("book1="+book1);
		System.out.println("book2="+book2);
		System.out.println("dbUtil="+dbUtil);
	}

}
