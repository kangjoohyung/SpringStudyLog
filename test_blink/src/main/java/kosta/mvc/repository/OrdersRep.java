package kosta.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import kosta.mvc.domain.Orders;
import kosta.mvc.domain.Users;

public interface OrdersRep extends JpaRepository<Orders, Long> , QuerydslPredicateExecutor<Orders> {

	List<Orders> findByUsersIdOrderByOrdersDateDesc(String usersId);
}
