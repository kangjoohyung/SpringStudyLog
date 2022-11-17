package kosta.mvc.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kosta.mvc.dto.TransferDTO;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransferDAOImpl implements TransferDAO {

	private final SqlSession session;
	
	@Override
	public int withDraw(TransferDTO transferDTO) {
		
		return session.update("TransferMapper.withDraw", transferDTO);
	}

	@Override
	public int deposit(TransferDTO transferDTO) {

		return session.update("TransferMapper.deposit", transferDTO);
	}

}
