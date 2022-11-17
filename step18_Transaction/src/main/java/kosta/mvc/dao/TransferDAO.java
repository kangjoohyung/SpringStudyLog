package kosta.mvc.dao;

import kosta.mvc.dto.TransferDTO;

public interface TransferDAO {

	/**
	 * 출금하기
	 */
	int withDraw(TransferDTO transferDTO);
	
	/**
	 * 입금하기
	 */
	int deposit(TransferDTO transferDTO);
	
}
