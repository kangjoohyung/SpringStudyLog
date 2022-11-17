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
	
	@Transactional(rollbackFor = MyException.class) //��� �޼ҵ� �����ϰ�ʹٸ� class���� �ֱ�
	@Override
	public int transfer(TransferDTO transferDTO) throws MyException {
		//����ϱ�
		int resultDraw=transferDAO.withDraw(transferDTO);
		
		//�Ա��ϱ�
		int resultDepo=transferDAO.deposit(transferDTO);
		
		//transaction ó���� ���� �⺻����Ǵ� RuntimeException�� �Ϻη� ����Ŵ
		//->MyException������ MyException���� ����غ���
		if(resultDraw==0||resultDepo==0)
			throw new MyException("������ü�� �����ϼ̽��ϴ�");
		
		return resultDraw;
	}

}
