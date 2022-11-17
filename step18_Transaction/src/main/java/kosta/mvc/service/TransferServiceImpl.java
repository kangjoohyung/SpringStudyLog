package kosta.mvc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kosta.mvc.dao.TransferDAO;
import kosta.mvc.dto.TransferDTO;
import kosta.mvc.exception.MyException;
import lombok.RequiredArgsConstructor;

//@Transactional(rollbackFor = MyException.class)
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

	private final TransferDAO transferDAO;
	
	@Transactional(rollbackFor = MyException.class) //모든 메소드 적용하고싶다면 class위에 넣기
	@Override
	public int transfer(TransferDTO transferDTO) throws MyException {
		//출금하기
		int resultDraw=transferDAO.withDraw(transferDTO);
		
		//입금하기
		int resultDepo=transferDAO.deposit(transferDTO);
		
		//transaction 처리를 위해 기본적용되는 RuntimeException을 일부러 일으킴
		//->MyException생성후 MyException으로 사용해보기
		if(resultDraw==0||resultDepo==0)
			throw new MyException("계좌이체를 실패하셨습니다");
		
		return resultDraw;
	}

}
