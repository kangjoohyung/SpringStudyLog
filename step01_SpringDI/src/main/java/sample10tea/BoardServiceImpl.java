package sample10tea;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component("boardService") //<bean class="" id="boardService"
@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	//@Autowired //byType -> byName
	@Resource(name = "boardDAOImpl")
    private BoardDAO boardDao; //?????
    
	@Autowired  //byType -> byName
    private BoardDAO boadMyBatisDAOImpl;
    
    public BoardServiceImpl() {
    	System.out.println("BoardServiceImpl()생성자 호출됨..");
    }
    
	@Override
	public void select() {
		boardDao.select();
		System.out.println("-----------------");
		boadMyBatisDAOImpl.select();

	}

}
