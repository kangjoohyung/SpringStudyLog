package kosta.mvc.exception;

public class MyException extends Exception{//체크예외=반드시 예외처리 필수
//public class MyException extends RuntimeException{//비체크예외=예외처리 선택
	public MyException() {}
	public MyException(String message) {
		super(message);
	}
}
