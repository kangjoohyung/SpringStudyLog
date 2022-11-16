package sample04;

public class StudentController {
	private StudentService service;
	private Student student;
	
	public void insert() {
		service.insert(student);
	}

	public void setService(StudentService service) {
		this.service = service;
		
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
