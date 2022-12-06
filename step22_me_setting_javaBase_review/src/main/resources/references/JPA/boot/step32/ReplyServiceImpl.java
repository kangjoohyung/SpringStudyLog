package references.JPA.boot.step32;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kosta.mvc.domain.Reply;
import kosta.mvc.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService {
	
	private final ReplyRepository replyRep;

	@Override
	public void insert(Reply reply) {
		replyRep.save(reply);

	}

	@Override
	public void delete(Long id) {
		replyRep.deleteById(id);

	}

}
