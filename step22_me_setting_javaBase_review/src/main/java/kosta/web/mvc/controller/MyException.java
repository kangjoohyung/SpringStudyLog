package kosta.web.mvc.controller;

public class MyException extends Exception{//üũ����=�ݵ�� ����ó�� �ʼ�
//public class MyException extends RuntimeException{//��üũ����=����ó�� ����
	public MyException() {}
	public MyException(String message) {
		super(message);
	}
}
