package sample04;

public class StudentDAOImpl implements StudentDAO {

	
	
	public StudentDAOImpl() {
		super();
		System.out.println("DAOImpl 생성자");
	}

	@Override
	public void insert(Student student) {
		System.out.println("DAOImpl insesrt");
		System.out.println(student);
	}

}
