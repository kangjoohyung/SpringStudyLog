package references.ajax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kosta.mvc.dto.Member;

//@Controller //일반 동기식
@RestController //Controller+ResponseBody / ajax용 클래스로 사용-> 동기식 일반 메소드는 빼야함(Home으로 옮김)
public class AjaxController {

	//@ResponseBody //ajax용 메소드에 넣을것->클래스쪽에 rest~~로 놔서 필요없어짐
	@RequestMapping(value="/load", produces="text/html;charset=UTF-8")
	public String load() {
		
		return "Ajax와 Spring의 만남";
	}
	
	@RequestMapping(value="/ajax", produces="text/html; charset=UTF-8")
	public String ajax(String name) {
		
		return name+"님 반가워요";
	}
	
	/**
	 * json 예제 메소드
	 * : 자바 객체를 응답해주기 위해서는 json으로 변환해주는 jacson Lib 필요하다
	 * -> jacson Lib를 추가하면 jackson lib가 자동으로 json으로 변환해서 응답해준다
	 */
	@RequestMapping("/json") //produces 넣지말기 : 잭슨라이브러리에 인코딩처리가 있어서, 둘다쓰면 충돌남. 위의 String은 라이브러리 안써서 써야했음
	public List<String> json(){
		return Arrays.asList("짬뽕", "짜장", "짬짜면", "탕수육", "깐쇼새우", "깐풍기"); 
		//lib추가전에 이렇게 보내면 406 에러남 : 데이터타입 안맞을때 나는 에러
		
	}
	
	@RequestMapping("/memberDTO")
	public Member dto() {
		
		return new Member("jang", "장희정", 22, "경기도 오리역");
	}
	
	@RequestMapping("/list")
	public List<Member> memberList(){
		List<Member> memberList=new ArrayList<Member>();
		memberList.add(new Member("jang1", "장희정1", 21, "경기도 오리역1"));
		memberList.add(new Member("jang2", "장희정2", 22, "경기도 오리역2"));
		memberList.add(new Member("jang3", "장희정3", 23, "경기도 오리역3"));
		memberList.add(new Member("jang4", "장희정4", 24, "경기도 오리역4"));
		memberList.add(new Member("jang5", "장희정5", 25, "경기도 오리역5"));
		
		return memberList;
	}
	
	/**
	 * map+list사용예제
	 */
	@RequestMapping("/map")
	public Map<String, Object> map(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message", "배고파요~");
		map.put("pageNo", 10);
		map.put("dto", new Member("jang", "장희정", 22, "경기도 오리역"));
		

		List<Member> memberList=new ArrayList<Member>();
		memberList.add(new Member("jang1", "장희정1", 21, "경기도 오리역1"));
		memberList.add(new Member("jang2", "장희정2", 22, "경기도 오리역2"));
		memberList.add(new Member("jang3", "장희정3", 23, "경기도 오리역3"));
		memberList.add(new Member("jang4", "장희정4", 24, "경기도 오리역4"));
		memberList.add(new Member("jang5", "장희정5", 25, "경기도 오리역5"));
		
		map.put("memberList", memberList);
		
		return map;
	}
}
