package kosta.mvc.service;

import org.springframework.stereotype.Service;

import kosta.mvc.domain.Reply;
import kosta.mvc.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyRepository replyRep;

	@Override
	public void insert(Reply reply) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
