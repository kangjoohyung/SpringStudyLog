package kosta.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kosta.mvc.domain.Orderdetails;
import kosta.mvc.domain.Orders;
import kosta.mvc.domain.Product;
import kosta.mvc.domain.Users;
import kosta.mvc.repository.OrdersRep;
import kosta.mvc.repository.ProductRep;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersRep ordersRep;
	@Autowired
	private ProductRep productRep;
	
	//마이페이지-10개 출력
	@Override
	public List<Orders> findByUsersIdOrderByOrdersDateDesc(String usersId){
		ordersRep.find
	}
	
	//마이페이지-기간별 조회
	@Override
	public List<Orders> mypageDetailByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	//인수로 세션의 유저 정보 받기(HttpSession session)-session.getAttribute(users)
	@Override
	public Orders checkBeforeOrders(Users users, Orders ordersProduct) {
		//반복문 사용해서 List 속의 상품들 꺼내서 비교하기
		List<Orderdetails> cartList=ordersProduct.getOrderdetailsList();
		for(Orderdetails orderdetails : cartList){
			//1) 멤버쉽 주문이 맞는지 조회 (Product ordersProduct)
			if(orderdetails.getProduct().getGoods().getGoodsMembershiponly()==1){
				if(users.getUsersMembership()==0) throw new RuntimeException("멤버쉽 회원만 주문 가능한 상품입니다");
			}
			//2) 상품이 멤버쉽카드인지 조회
			if(orderdetails.getProduct().getProductCode()=="멤버쉽카드의 상품코드"){
				if(users.getUsersMembership()==1) throw new RuntimeException("이미 멤버쉽 회원입니다");
			}

			//3) 상품 재고량이 주문 가능한 숫자인지 조회
			Optional<Product> product=productRep.findById(orderdetails.getProduct().getProductCode());
			if(product.get().getProductStock()>=0){
				if(orderdetails.getProduct().getProductStock()>product.get().getProductStock() || product.get().getProductStock()==0)
						throw new RuntimeException("상품 재고량이 부족합니다. 개수를 확인해주세요.");
			}
			
		}//장바구니 리스트 꺼내서 유효성 체크하는 for문끝
			
		return ordersProduct;
	}//checkBeforeOrders(Users users, Orders ordersProduct) end;


	
}//class end
