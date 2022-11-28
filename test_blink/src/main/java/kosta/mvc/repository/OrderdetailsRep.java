package kosta.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import kosta.mvc.domain.Orderdetails;

public interface OrderdetailsRep extends JpaRepository<Orderdetails, Long> , QuerydslPredicateExecutor<Orderdetails> {

}
