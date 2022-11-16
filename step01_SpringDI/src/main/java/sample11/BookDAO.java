package sample11;

public interface BookDAO {
   void save(EmailSender emailSender, MessageSender messageSender, 
		       BookDTO book1, BookDTO book2);
}
