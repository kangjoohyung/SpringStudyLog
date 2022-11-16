package sample10;

public class BoardMyBatisDAOImpl implements BoardDAO {

	
	public BoardMyBatisDAOImpl() {
		super();
		System.out.println("BoardMyBatisDAOImpl()");
	}

	@Override
	public void select() {
		System.out.println("BoardMyBatisDAOImpl()의 select()");

	}

}
