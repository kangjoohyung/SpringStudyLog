package sample11;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("controller") //id="controller"
public class BookController {
	
	@Resource(name = "bookService")//name은 주입될 객체의 id의 값
    private BookService bookService;
	
	@Autowired
    private BookDTO book1;
	
	@Autowired
    private BookDTO book2;
    
    
    public void invoker() {
    	bookService.save(book1, book2);
    }
}



