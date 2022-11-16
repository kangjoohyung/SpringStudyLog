package sample08;

public class BookController {
	private BookService bookService; 
	private BookDTO book1;
	private BookDTO book2;

	public BookController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BookController(BookService bookService, BookDTO book1, BookDTO book2) {
		super();
		this.bookService = bookService;
		this.book1 = book1;
		this.book2 = book2;
	}

	public void invoker() {
		bookService.save(book1, book2);
	}
	
}
