package kosta.mvc.exception;
/**
 * ��ǰ�ڵ尡 �ߺ��ǰų� ������ ������ ������� �߻��ϴ� ���� Ŭ���� 
 * */

public class MyErrorException extends Exception {
    public MyErrorException() {}
    public MyErrorException(String message) {
    	super(message);
    }
}
