package kosta.mvc.exception;
/**
 * 상품코드가 중복되거나 가격의 범위가 벗어났을때 발생하는 예외 클래스 
 * */

public class MyErrorException extends Exception {
    public MyErrorException() {}
    public MyErrorException(String message) {
    	super(message);
    }
}
