package kosta.mvc.service;

import kosta.mvc.dto.TransferDTO;
import kosta.mvc.exception.MyException;

public interface TransferService {

	/**
	 * ������ü : ���+�Ա�
	 */
	int transfer(TransferDTO transferDTO) throws MyException;
}
