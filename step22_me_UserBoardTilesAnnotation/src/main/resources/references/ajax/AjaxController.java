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

//@Controller //�Ϲ� �����
@RestController //Controller+ResponseBody / ajax�� Ŭ������ ���-> ����� �Ϲ� �޼ҵ�� ������(Home���� �ű�)
public class AjaxController {

	//@ResponseBody //ajax�� �޼ҵ忡 ������->Ŭ�����ʿ� rest~~�� ���� �ʿ������
	@RequestMapping(value="/load", produces="text/html;charset=UTF-8")
	public String load() {
		
		return "Ajax�� Spring�� ����";
	}
	
	@RequestMapping(value="/ajax", produces="text/html; charset=UTF-8")
	public String ajax(String name) {
		
		return name+"�� �ݰ�����";
	}
	
	/**
	 * json ���� �޼ҵ�
	 * : �ڹ� ��ü�� �������ֱ� ���ؼ��� json���� ��ȯ���ִ� jacson Lib �ʿ��ϴ�
	 * -> jacson Lib�� �߰��ϸ� jackson lib�� �ڵ����� json���� ��ȯ�ؼ� �������ش�
	 */
	@RequestMapping("/json") //produces �������� : �轼���̺귯���� ���ڵ�ó���� �־, �Ѵپ��� �浹��. ���� String�� ���̺귯�� �ȽἭ �������
	public List<String> json(){
		return Arrays.asList("«��", "¥��", "«¥��", "������", "������", "��ǳ��"); 
		//lib�߰����� �̷��� ������ 406 ������ : ������Ÿ�� �ȸ����� ���� ����
		
	}
	
	@RequestMapping("/memberDTO")
	public Member dto() {
		
		return new Member("jang", "������", 22, "��⵵ ������");
	}
	
	@RequestMapping("/list")
	public List<Member> memberList(){
		List<Member> memberList=new ArrayList<Member>();
		memberList.add(new Member("jang1", "������1", 21, "��⵵ ������1"));
		memberList.add(new Member("jang2", "������2", 22, "��⵵ ������2"));
		memberList.add(new Member("jang3", "������3", 23, "��⵵ ������3"));
		memberList.add(new Member("jang4", "������4", 24, "��⵵ ������4"));
		memberList.add(new Member("jang5", "������5", 25, "��⵵ ������5"));
		
		return memberList;
	}
	
	/**
	 * map+list��뿹��
	 */
	@RequestMapping("/map")
	public Map<String, Object> map(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("message", "����Ŀ�~");
		map.put("pageNo", 10);
		map.put("dto", new Member("jang", "������", 22, "��⵵ ������"));
		

		List<Member> memberList=new ArrayList<Member>();
		memberList.add(new Member("jang1", "������1", 21, "��⵵ ������1"));
		memberList.add(new Member("jang2", "������2", 22, "��⵵ ������2"));
		memberList.add(new Member("jang3", "������3", 23, "��⵵ ������3"));
		memberList.add(new Member("jang4", "������4", 24, "��⵵ ������4"));
		memberList.add(new Member("jang5", "������5", 25, "��⵵ ������5"));
		
		map.put("memberList", memberList);
		
		return map;
	}
}
