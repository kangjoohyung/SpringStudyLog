package sample10;

public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;
	private BoardDAO boardMyBatisDAOImpl;
	
	public BoardServiceImpl() {
		super();
		System.out.println("BoardServiceImpl()");
	}

	@Override
	public void select() {
		boardDAO.select();
		System.out.println("--------------");
		boardMyBatisDAOImpl.select();
		
	}
	
}
