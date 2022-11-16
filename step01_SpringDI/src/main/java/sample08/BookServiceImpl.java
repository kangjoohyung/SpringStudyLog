package sample08;

public class BookServiceImpl implements BookService {
	private EmailSender emailSender; //멤버필드에 있기때문에 주입 가능
	private MessageSender messageSender; //주입-set
	private BookDAO bookDAO; //주입-생성자
	
	public BookServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BookServiceImpl(BookDAO bookDAO) {
		super();
		this.bookDAO = bookDAO;
	}


	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}


	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}


	@Override
	public void save(BookDTO book1, BookDTO book2) {
		// TODO Auto-generated method stub
		bookDAO.save(emailSender, messageSender, book1, book2);
	}
}
