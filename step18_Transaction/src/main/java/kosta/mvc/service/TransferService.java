package kosta.mvc.service;

import kosta.mvc.dto.TransferDTO;
import kosta.mvc.exception.MyException;

public interface TransferService {

	/**
	 * 계좌이체 : 출금+입금
	 */
	int transfer(TransferDTO transferDTO) throws MyException;
}
