package kosta.mvc.exception;

public class MyException extends Exception{//üũ����=�ݵ�� ����ó�� �ʼ�
//public class MyException extends RuntimeException{//��üũ����=����ó�� ����
	public MyException() {}
	public MyException(String message) {
		super(message);
	}
}
