package kosta.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kosta.mvc.domain.Product;

public interface ProductRep extends JpaRepository<Product, String> , QuerydslPredicateExecutor<Product>{
	
}
