package sample04;

public class StudentServiceImpl implements StudentService {

	private StudentDAO studentDAO;
	
	public StudentServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public void insert(Student student) {
		// TODO Auto-generated method stub
		studentDAO.insert(student);
	}

}
