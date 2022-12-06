package references.sample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Commit;

import web.mvc.domain.Orderdetails;
import web.mvc.domain.Orders;
import web.mvc.domain.Product;
import web.mvc.domain.Users;
import web.mvc.repository.OrderdetailsRepository;
import web.mvc.repository.OrdersRepository;
import web.mvc.repository.ProductRepository;
import web.mvc.repository.UserRepository;

@SpringBootTest
@Transactional
@Commit
class TestOrdersService {

	@Autowired
	private OrdersRepository ordersRep;
	@Autowired
	private ProductRepository productRep;
	@Autowired
	private OrderdetailsRepository orderdetailsRep;
	@Autowired
	private UserRepository userRep;

	@Test
	void contextLoads() {
	}
	
	@Test/**테스트완0.1->users넣는건 성공*///
	void insertUsers() {
		userRep.save(new Users("user", 0, null));
	}
	
	@Test/**테스트완0.2->product*///
	void insertProduct() {
		
		productRep.save(Product.builder()
				.productCode("A01")
				.productName("상품1")
				.productEngCode("pro1")
				.productStock(3000)
				.build());
		productRep.save(Product.builder()
				.productCode("A02")
				.productName("상품2")
				.productEngCode("pro2")
				.productStock(0)
				.build());
		productRep.save(Product.builder()
				.productCode("A03")
				.productName("상품3")
				.productEngCode("pro3")
				.productStock(-1)
				.build());
		
	}
	
	@Test/**테스트중*/
	public void /* Page<Orders> */ selectAllOrders(/*
													 * int inCase, Users users, String startDate, String finalDate,
													 * Pageable pageable
													 */) {
		
		Page<Orders> ordersList=null;
//		String usersId=null;
		/**테스트(인수넣어서)*/
		Pageable pageable=PageRequest.of(0, 10, Direction.DESC, "ordersDate");
		Users users=userRep.findById("user").orElse(null);
		
		//////////////////////////////////////////////////////////
		String startDate="20221129 00:00:00";
		String finalDate="20221202 00:00:00";
		
		int inCase=3;
		
		////////////////////////////////////
		if(inCase==1) { //관리자페이지-main 주문내역(페이징처리)
			
			ordersList=ordersRep.findAll(pageable);
			
		}else if(inCase==2) { //마이페이지-main 주문내역 10개 출력용 메소드		
//			usersId=users.getUsersId();
//			Pageable mainPage=PageRequest.of(0, 10, Direction.DESC, "ordersDate");
			
//			ordersList=ordersRep.findByUsersId(users, mainPage);
			ordersList=ordersRep.findByUsers(users, pageable);
			
		}else if(inCase==3) { //마이페이지-기간별 주문내역 조회	
//			usersId=users.getUsersId();
			
			//String형식 날짜 Date로 변환
//			SimpleDateFormat dateFormat= new SimpleDateFormat ("yyyyMMdd");

			//String형식을 LocalDateTime으로 변환
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
			
			LocalDateTime dateStartDate=null;
			LocalDateTime dateFinalDate=null;
			LocalDateTime finalPlus=null;
			
			try {
//				dateStartDate=LocalDateTime.parse(startDate);
//				dateFinalDate=LocalDateTime.parse(finalDate);
				
				dateStartDate=LocalDateTime.parse(startDate, formatter);
				dateFinalDate=LocalDateTime.parse(finalDate, formatter);
				finalPlus=dateFinalDate.plusDays(1);
			}catch (Exception e) {
				e.printStackTrace();
//				throw new RuntimeException("날짜 형식을 확인해주세요 ex)20221128");
			}
			ordersList=ordersRep
					.findByUsersAndOrdersDateGreaterThanEqualAndOrdersDateLessThan
					(users, dateStartDate, finalPlus, pageable);
		}
		
		/**테스트코드*/

		ordersList.forEach(b->System.out.println(b));
		
		/////////////////////////////////////////
		
//		return ordersList;
	} //selectAllOrders end

	@Test/**테스트 완*/
	public void/*Page<Orderdetails>*/ selectAllOrderdetails(/*Long ordersNo, Pageable pageable*/) {
		/*테스트용 추가*/
		Long ordersNo=3L;
		Pageable pageable=PageRequest.of(0, 10, Direction.DESC, "orderdetailsNo");
		//////////////////////////////////////////////////////
		Orders orders=ordersRep.findById(ordersNo).orElse(null);
		
		Page<Orderdetails> orderdetailsList=orderdetailsRep.findByOrders(orders, pageable);
		
		//////////////////////////////////////////////////////////
		/*테스트용*/
		System.out.println("****************");
		System.out.println("orderdetailsList"+orderdetailsList);
		orderdetailsList.forEach(b->System.out.println(b));
		
//		return orderdetailsList;
		
	} //selectAllOrdersdetails end

	/* 체크만 한 후 RuntimeException 처리
	 * - 1) 멤버쉽 체크는 시큐리티 사용해야돼서 뷰->컨트롤러로 카트 담을 때 분류하는게 좋을듯 (1번 삭제)
	 * */
	/* 예외처리 안할거면 selectCheckBeforeOrders에서 String으로 "forward:" 리턴하여, 
	 * 아래 insertOrdersOrderdetails에서 Runtime대신 기본 리턴값 "redirect:" 를 리턴하여 받아서
	 * 장바구니에 담는 곳으로 되돌리는 경로 설정하면 어떨까 하지만,
	 * 우선 RunTimeException으로 설정*/
	@Test/** 멤버쉽카드인지 는 확인 못함 */
	public void selectCheckBeforeOrders(Users users, Orders ordersProduct) {
		//반복문 사용해서 List 속의 상품들 꺼내서 비교하기
				List<Orderdetails> cartList=ordersProduct.getOrderdetailsList();
				for(Orderdetails orderdetails : cartList){
//					//1) 멤버쉽 주문이 맞는지 조회 (Product ordersProduct)
//					if(orderdetails.getProduct().getGoods().getGoodsMemberShiponly()==1){
//						if(users.getUsersMemberShip()==0) throw new RuntimeException("멤버쉽 회원만 주문 가능한 상품입니다");
//					}
					
					//2) 상품이 멤버쉽카드인지 조회
					if(orderdetails.getProduct().getProductCode()=="멤버쉽카드의 상품코드"){
						if(users.getUsersMemberShip()==1) throw new RuntimeException("이미 멤버쉽 회원입니다");
					}

					//3) 상품 재고량이 주문 가능한 숫자인지 조회
					Product product=productRep.findById(orderdetails.getProduct().getProductCode()).orElse(null);
					if(product.getProductStock()>=0){
						if(orderdetails.getProduct().getProductStock()>product.getProductStock() || product.getProductStock()==0)
								throw new RuntimeException("상품 재고량이 부족합니다. 개수를 확인해주세요.");
					}
					
				}//장바구니 리스트 꺼내서 유효성 체크하는 for문끝
	} //selectCheckBeforeOrders end
	
	/**테스트 찾기*/
	@Test
	public void findUsersTest() {
		Users users=userRep.findById("user").orElse(null);
		System.out.println("-----------------------------");
		System.out.println("Users users="+users);
	}
	/**테스트 찾기*/
	@Test
	public void findProductTest() {
		Product product=productRep.findById("A01").orElse(null);
		System.out.println("-----------------------------");
		System.out.println("Product product="+product);
	}

	@Test/**인수 받아서 출력 테스트, 체크메소드 호출 : */
	public void beforeInsertpublic(/*Users users, Orders ordersProduct*/ /*, List<Orderdetails> cartList*/) {
		/**테스트용 인수*/
		Users users=userRep.findById("user").orElse(null);
		System.out.println("**********************");
		System.out.println("test-insert-users담기");
		System.out.println("users="+users);
		
		Orders ordersProduct=Orders.
				builder()
				.users(users)
				.ordersAddr("주소1")
				.ordersReceiverName("받는자")
				.ordersReceiverPhone("01011111111")
				.ordersZipcode("12312")
				.ordersStatus("결제완료").build();
		
		System.out.println("**********************");
		System.out.println("test-insert-객체에 레코드 저장");
		
		List<Orderdetails> cartList=new ArrayList<Orderdetails>();
		for (int i = 0; i <=6 ; i++) {
			Product product=Product.builder().productCode("A01").build();
			System.out.println("for문 cartList담는중");
			cartList.add(Orderdetails.builder()
					.orders(ordersProduct)
					.product(product)
					.orderdetailsPrice(3000)
					.orderdetailsQty(2)
					.build());
		}
		System.out.println("**********************");
		System.out.println("test-insert-for직후");

		ordersProduct.setOrderdetailsList(cartList);
		
		System.out.println(ordersProduct);
		ordersProduct.getOrderdetailsList().forEach(b->System.out.println(b));
	}
	
	//인수로 Orders에 한번에 담을 수 있는지 확인 후 안담아지면
	//List로 주문상세정보 받아서 추가로 담기
	@Test/**테스트1 완료!!->멤버쉽카드만 못함 */
	public void insertOrdersOrderdetails(/*Users users, Orders ordersProduct*//*, List<Orderdetails> cartList*/) {
		/**테스트용 인수*/
		/**테스트용 인수*/
		Users users=userRep.findById("user").orElse(null);
		System.out.println("**********************");
		System.out.println("test-insert-users담기");
		System.out.println("users="+users);
		
		Orders ordersProduct=Orders.
				builder()
				.users(users)
				.ordersAddr("주소1")
				.ordersReceiverName("받는자")
				.ordersReceiverPhone("01011111111")
				.ordersZipcode("12312")
				.ordersStatus("결제완료").build();
		
		System.out.println("**********************");
		System.out.println("test-insert-객체에 레코드 저장");
		
		List<Orderdetails> cartList=new ArrayList<Orderdetails>();
		for (int i = 1; i <6 ; i++) {
			Product product=Product.builder().productCode("A03").build();
			System.out.println("for문 cartList담는중");
			cartList.add(Orderdetails.builder()
					.orders(ordersProduct)
					.product(product)
					.orderdetailsPrice(3000)
					.orderdetailsQty(2)
					.build());
		}
		System.out.println("**********************");
		System.out.println("test-insert-for직후");

		ordersProduct.setOrderdetailsList(cartList);
		
		System.out.println(ordersProduct);
		ordersProduct.getOrderdetailsList().forEach(b->System.out.println(b));
		
		//////////////////////////////////////////
		
		//주문 체크 메소드 호출하여 주문전 체크
		this.selectCheckBeforeOrders(users, ordersProduct);
		//이상없다면 Exception없이 빠져나옴
		
		String usersId=users.getUsersId();

		
		//insert
		Orders finishOrders=ordersRep.save(ordersProduct); //한 번에 insert 되면 이걸로 끝내기
		
		//상세정보 따로 담아야 하면 쓰기
		List<Orderdetails> orderdetailsList=finishOrders.getOrderdetailsList();
		
		//저장값 꺼내서 상품에 해당하는 상품 재고량 감소 및 멤버쉽 수정 구현
		//if 재고량이 1이상일때 : 재고량 감소 / 재고량-상품수량<0 이면 실패
		//멤버쉽카드일시 :  위 재고량 로직+ 권한생성 + 회원 멤버쉽 1로 update
		List<Orderdetails> finishOrderdetailsList=orderdetailsRep.saveAll(orderdetailsList);
		
		for(Orderdetails orderdetails : finishOrderdetailsList){
			
			//상품 수정 기능구현을 위한 상품 조회
			String getProdCode=orderdetails.getProduct().getProductCode();
			Product product=productRep.findById(getProdCode).orElse(null);

			//1-1) 상품이 멤버쉽카드인지 조회
			//1-2) 멤버쉽 수정 기능구현을 위한 유저 조회 (혹은 인수 사용->인수에 모든 정보 없을듯하니 조회하기)
			//1-3) 유저 정보의 멤버쉽 업데이트
			if(getProdCode=="멤버쉽카드의 상품코드"){
				Users dbUsers=userRep.findById(usersId).orElse(null);
				dbUsers.setUsersMemberShip(1);
			}
			/*테스트 완료*/
			//2-1) 상품 재고량이 주문 가능한 숫자인지 조회 (0개 초과부터)
			//2-2) 상품 재고량 감소
			if(product.getProductStock()>0){
				product.setProductStock(product.getProductStock()-orderdetails.getOrderdetailsQty());
			}
			
		}//장바구니 리스트 꺼내서 유효성 체크하는 for문끝

		/**테스트 insert 출력*/
		finishOrderdetailsList.forEach(b->System.out.println(b));
		////////////////////////////////
		
	} //insertOrdersOrderdetails end

}
