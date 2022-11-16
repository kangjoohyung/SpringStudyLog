package sample03;

public class BookVo {

	private String subject;
	private String writer;
	private int price;
	private String date;
	
	public BookVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookVo(String subject, String writer, int price, String date) {
		super();
		this.subject = subject;
		this.writer = writer;
		this.price = price;
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public String getWriter() {
		return writer;
	}

	public int getPrice() {
		return price;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "과목 : " + subject + "\n작성자 : " + writer + "\n가격 : " + price + "\n날짜 : " + date;
	}
	
}
