package references.JPA.boot.step32;

import org.springframework.data.jpa.repository.JpaRepository;

import kosta.mvc.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
