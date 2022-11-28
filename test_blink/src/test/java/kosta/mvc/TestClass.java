package kosta.mvc;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;

import jakarta.transaction.Transactional;
import kosta.mvc.domain.Orders;
import kosta.mvc.repository.OrdersRep;

@SpringBootTest
@Transactional
@Commit
public class TestClass {

	@Autowired
	private OrdersRep ordersRep;
	
	@Test
	public void mypageMainList() {
		Pageable pageable=PageRequest.of(0, 10);
		Page<Orders> page=(Page<Orders>)ordersRep.findByUsersIdOrderByOrdersDateDesc("ss");
		List<Orders> list=page.getContent();
		System.out.println(list);
	}
	
}
