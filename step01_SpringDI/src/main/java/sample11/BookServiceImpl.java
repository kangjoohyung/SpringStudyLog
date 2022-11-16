package sample11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")  //id="bookService"
public class BookServiceImpl implements BookService {
	
	@Autowired //byType --> byName
    private BookDAO bookDao;
	
	@Autowired
    private EmailSender emailSender;
	
	@Autowired
    private MessageSender messageSender;
    
    
	@Override
	public void save(BookDTO book1, BookDTO book2) {
		bookDao.save(emailSender, messageSender, book1, book2);

	}

}
